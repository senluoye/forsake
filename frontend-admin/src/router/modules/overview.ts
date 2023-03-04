/*
 * @Author: senluoye 1599869435@qq.com
 * @Date: 2023-03-04 16:16:54
 * @LastEditors: senluoye 1599869435@qq.com
 * @LastEditTime: 2023-03-04 16:37:34
 * @FilePath: \frontend-admin\src\router\modules\overview.ts
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import { lazy } from 'react';
import { ViewModuleIcon } from 'tdesign-icons-react';
import { IRouter } from '../index';

const overview: IRouter[] = [
  {
    path: '/overview',
    meta: {
      title: '总览',
      Icon: ViewModuleIcon,
    },
    Component: lazy(() => import('pages/Overview')),
  },
];

export default overview;
