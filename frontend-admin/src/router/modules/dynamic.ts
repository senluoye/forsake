import { lazy } from 'react';
import { ViewModuleIcon } from 'tdesign-icons-react';
import { IRouter } from '../index';

const result: IRouter[] = [
  {
    path: '/dynamic',
    Component: lazy(() => import('pages/Dynamic')),
    meta: {
      title: '学习动态',
      Icon: ViewModuleIcon,
    },
  },
];

export default result;
