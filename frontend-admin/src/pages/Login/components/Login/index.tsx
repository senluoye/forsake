import React, { useState, useRef, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Form, MessagePlugin, Input, Checkbox, Button, FormInstanceFunctions, SubmitContext } from 'tdesign-react';
import { LockOnIcon, UserIcon, BrowseOffIcon, BrowseIcon } from 'tdesign-icons-react';
import classnames from 'classnames';
import { useAppDispatch } from 'modules/store';
import { login } from 'modules/user';
import Style from './index.module.less';
import axios from 'axios';

const { FormItem } = Form;

export default function Login() {
  const [showPsw, toggleShowPsw] = useState(false);
  const formRef = useRef<FormInstanceFunctions>();
  // 用于路由跳转
  const navigate = useNavigate();
  const dispatch = useAppDispatch();

  useEffect(() => {
    (async () => {
      const token = localStorage.getItem('token');
      if (token == null || token === '') return;

      const { data } = await axios.get('/api/admin/user/info', {
        headers: {
          token,
        },
      });

      if (data.code === 0) {
        navigate('/user');
      } else if (data.code === -1) {
        MessagePlugin.error('登录过期, 请重新登录', 1500);
        localStorage.setItem('token', '');
      }
    })();
  }, []);

  const onSubmit = async (e: SubmitContext) => {
    if (e.validateResult === true) {
      try {
        const formValue = formRef.current?.getFieldsValue?.(true) || {};
        const { payload } = await dispatch(login(formValue));
        const { token } = payload;
        if (token) {
          console.log(token);
          MessagePlugin.success('登录成功');
          localStorage.setItem('token', token);
          navigate('/user');
          return;
        }
        MessagePlugin.error('登录失败,请检查账号密码');
      } catch (e) {
        console.log(e);
        MessagePlugin.error('登录失败');
      }
    }
    localStorage.setItem('token', '');
  };

  return (
    <div>
      <Form
        ref={formRef}
        className={classnames(Style.itemContainer, `login-password`)}
        labelWidth={0}
        onSubmit={onSubmit}
      >
        {
          <>
            <FormItem name='code' rules={[{ required: true, message: '账号必填', type: 'error' }]}>
              <Input size='large' placeholder='请输入账号' prefixIcon={<UserIcon />}></Input>
            </FormItem>
            <FormItem name='password' rules={[{ required: true, message: '密码必填', type: 'error' }]}>
              <Input
                size='large'
                type={showPsw ? 'text' : 'password'}
                clearable
                placeholder='请输入登录密码'
                prefixIcon={<LockOnIcon />}
                suffixIcon={
                  showPsw ? (
                    <BrowseIcon onClick={() => toggleShowPsw((current) => !current)} />
                  ) : (
                    <BrowseOffIcon onClick={() => toggleShowPsw((current) => !current)} />
                  )
                }
              />
            </FormItem>
            <div className={classnames(Style.checkContainer, Style.rememberPwd)}>
              <Checkbox>记住账号</Checkbox>
              <span className={Style.checkContainerTip}>忘记账号？</span>
            </div>
          </>
        }
        {
          <FormItem className={Style.btnContainer}>
            <Button block size='large' type='submit'>
              登录
            </Button>
          </FormItem>
        }
      </Form>
    </div>
  );
}
