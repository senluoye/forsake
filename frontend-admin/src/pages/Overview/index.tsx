/*
 * @Author: senluoye 1599869435@qq.com
 * @Date: 2023-03-04 16:20:06
 * @LastEditors: senluoye 1599869435@qq.com
 * @LastEditTime: 2023-03-04 16:21:57
 * @FilePath: \frontend-admin\src\pages\Overview\index.tsx
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import React, { memo, useEffect, useState } from 'react';
import { MessagePlugin, Pagination, Select, Loading } from 'tdesign-react';
import CommonStyle from 'styles/common.module.less';
import classnames from 'classnames';

import styles from './index.module.less';
import axios from 'axios';
import { BrowserRouterProps, useNavigate } from 'react-router-dom';

const Overview: React.FC<BrowserRouterProps> = () => {
  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  interface Overview {
    
  }

  /**
   * 检查token
   * @returns
   */
  const checkToken = () => {
    if (token == null || token === '') {
      navigate('/login');
      return false;
    }
    return true; 
  };

  const getOverview = () => {};

  useEffect(() => {}, []);

  return <div>数据总览</div>;
};

export default memo(Overview);
