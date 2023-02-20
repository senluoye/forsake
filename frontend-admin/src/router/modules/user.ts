import { lazy } from 'react';
import { UserCircleIcon } from 'tdesign-icons-react';
import { IRouter } from '../index';

const result: IRouter[] = [
  {
    path: '/user',
    Component: lazy(() => import('pages/User')),
    meta: {
      title: '个人中心',
      Icon: UserCircleIcon,
    },
  },
];

export default result;
