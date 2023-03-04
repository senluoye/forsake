/*
 * @Author: senluoye 1599869435@qq.com
 * @Date: 2023-02-06 11:52:53
 * @LastEditors: senluoye 1599869435@qq.com
 * @LastEditTime: 2023-03-01 20:56:44
 * @FilePath: \frontend-admin\src\layouts\components\Header\HeaderIcon.tsx
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
 */
import React, { memo } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Popup, Badge, Dropdown, Space } from 'tdesign-react';
import {
  Icon,
  LogoGithubIcon,
  MailIcon,
  HelpCircleIcon,
  SettingIcon,
  PoweroffIcon,
  UserCircleIcon,
} from 'tdesign-icons-react';
import { useAppDispatch } from 'modules/store';
import { toggleSetting } from 'modules/global';
import { logout } from 'modules/user';
import Style from './HeaderIcon.module.less';

const { DropdownMenu, DropdownItem } = Dropdown;

export default memo(() => {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();

  const gotoWiki = () => {
    window.open('https://tdesign.tencent.com/react/overview');
  };

  const gotoGitHub = () => {
    window.open('https://github.com/senluoye/forsake/tree/main/frontend-admin');
  };

  const clickHandler = (data: any) => {
    if (data.value === 1) {
      navigate('/user');
    }
  };
  const handleLogout = async () => {
    await dispatch(logout());
    navigate('/login/index');
  };

  return (
    <Space align='center'>
      <Popup content='代码仓库' placement='bottom' showArrow destroyOnClose>
        <Button
          className={Style.menuIcon}
          shape='square'
          size='large'
          variant='text'
          onClick={gotoGitHub}
          icon={<LogoGithubIcon />}
        />
      </Popup>
      <Dropdown trigger={'click'} onClick={clickHandler}>
        <Button variant='text' className={Style.dropdown}>
          <Icon name='user-circle' className={Style.icon} />
          <span className={Style.text}>admin</span>
          <Icon name='chevron-down' className={Style.icon} />
        </Button>
        <DropdownMenu>
          <DropdownItem value={1}>
            <div className={Style.dropItem}>
              <UserCircleIcon />
              <span>个人中心</span>
            </div>
          </DropdownItem>
          <DropdownItem value={1} onClick={handleLogout}>
            <div className={Style.dropItem}>
              <PoweroffIcon />
              <span>退出登录</span>
            </div>
          </DropdownItem>
        </DropdownMenu>
      </Dropdown>
      <Popup content='页面设置' placement='bottom' showArrow destroyOnClose>
        <Button
          className={Style.menuIcon}
          shape='square'
          size='large'
          variant='text'
          onClick={() => dispatch(toggleSetting())}
          icon={<SettingIcon />}
        />
      </Popup>
    </Space>
  );
});
