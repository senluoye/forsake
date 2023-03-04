import { lazy } from 'react';
import { ViewModuleIcon } from 'tdesign-icons-react';
import { IRouter } from '../index';

const message: IRouter[] = [
  {
    path: '/message',
    Component: lazy(() => import('pages/Dynamic')),
    meta: {
      title: '通知',
      Icon: ViewModuleIcon,
    },
  },
];

export default message;
