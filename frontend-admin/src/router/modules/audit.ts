import { lazy } from 'react';
import { ViewModuleIcon } from 'tdesign-icons-react';
import { IRouter } from '../index';

const audit: IRouter[] = [
  {
    path: '/audit',
    meta: {
      title: '审核',
      Icon: ViewModuleIcon,
    },
    children: [
      {
        path: '/dynamic',
        Component: lazy(() => import('pages/Dynamic')),
        meta: {
          title: '学习',
          Icon: ViewModuleIcon,
        },
      },
      {
        path: '/competition',
        Component: lazy(() => import('pages/Dynamic')),
        meta: {
          title: '比赛',
          Icon: ViewModuleIcon,
        },
      },
      {
        path: '/work',
        Component: lazy(() => import('pages/Dynamic')),
        meta: {
          title: '创业/就业',
          Icon: ViewModuleIcon,
        },
      },
    ],
  },
];

export default audit;
