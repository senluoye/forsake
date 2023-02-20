import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import { RootState } from '../store';
import axios from 'axios';
import request from 'configs/host';

const { API } = request.forsake;
const namespace = 'user';
const TOKEN_NAME = 'token';

const initialState = {
  token: localStorage.getItem(TOKEN_NAME) || 'main_token', // 默认token不走权限
  userInfo: {},
};

/**
 * 登陆
 */
export const login = createAsyncThunk(`${namespace}/login`, async (userInfo: Record<string, unknown>) => {
  const { data } = await axios.post(`${API}/api/admin/user/login`, userInfo);
  if (data.code === 0) {
    const { data: token } = data;
    if (token) {
      return token;
    }
  }
  return '';
});

/**
 * 获取用户信息
 */
export const getUserInfo = createAsyncThunk(`${namespace}/getUserInfo`, async (_, { getState }: any) => {
  const { token } = getState();
  const mockRemoteUserInfo = async (token: string) => {
    if (token === 'main_token') {
      return {
        name: 'td_main',
        roles: ['all'],
      };
    }
    return {
      name: 'td_dev',
      roles: ['userIndex', 'dashboardBase', 'login'],
    };
  };

  const res = await mockRemoteUserInfo(token);

  return res;
});

const userSlice = createSlice({
  name: namespace,
  initialState,
  reducers: {
    logout: (state) => {
      localStorage.removeItem(TOKEN_NAME);
      state.token = '';
      state.userInfo = {};
    },
    remove: (state) => {
      state.token = '';
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(login.fulfilled, (state, action) => {
        localStorage.setItem(TOKEN_NAME, action.payload);

        state.token = action.payload;
      })
      .addCase(getUserInfo.fulfilled, (state, action) => {
        state.userInfo = action.payload;
      });
  },
});

export const selectListBase = (state: RootState) => state.listBase;

export const { logout, remove } = userSlice.actions;

export default userSlice.reducer;
