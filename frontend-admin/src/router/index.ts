import React, { lazy } from 'react';
import { BrowserRouterProps } from 'react-router-dom';

import dashboard from './modules/dashboard';
import dynamic from './modules/dynamic';
import form from './modules/form';
import detail from './modules/detail';
import result from './modules/result';
import list from './modules/list';
import user from './modules/user';
// import login from './modules/login';
import otherRoutes from './modules/others';

export interface IRouter {
  path: string;
  redirect?: string;
  // React.FC 表示函数式组件，是一个泛型
  Component?: React.FC<BrowserRouterProps> | (() => any);
  /**
   * 当前路由是否全屏显示
   */
  isFullPage?: boolean;
  /**
   * meta未赋值 路由不显示到菜单中
   */
  meta?: {
    title?: string;
    Icon?: React.FC;
    /**
     * 侧边栏隐藏该路由
     */
    hidden?: boolean;
    /**
     * 单层路由
     */
    single?: boolean;
  };
  children?: IRouter[];
}

const routes: IRouter[] = [
  {
    path: '/login',
    Component: lazy(() => import('pages/Login')),
    isFullPage: true,
    meta: {
      hidden: true,
    },
  },
  {
    path: '/',
    redirect: '/login',
  },
];

const allRoutes = [...routes, ...list, ...dynamic, ...user, ...otherRoutes];

export default allRoutes;
