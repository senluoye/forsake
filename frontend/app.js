// app.js
import { promisifyAll } from 'miniprogram-api-promise';
// import Message from 'tdesign-miniprogram/message/index';

App({
  onLaunch() {
    // 展示本地存储能力
    const wxp = wx.p = {};
    promisifyAll(wx, wxp);
  },

  globalData: {}
})
