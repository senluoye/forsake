import React, { memo, useEffect, useState } from 'react';
import { Row, Col, Button, Card, MessagePlugin } from 'tdesign-react';
import { IconFont } from 'tdesign-icons-react';
import { BrowserRouterProps, useNavigate } from 'react-router-dom';
import ReactEcharts from 'echarts-for-react';
import { visitData } from './chart';
import useDynamicChart from 'hooks/useDynamicChart';

import styles from './index.module.less';
import axios from 'axios';

interface IUser {
  id: number;
  createAt: string;
  updateAt: string;
  deleted: number;

  name: string;
  code: string;
  email: string;
  phone: string;

  superiorCode: string;
}

const tempUser: IUser = {
  id: 1,
  createAt: '2023-02-08T18:31:14.000+00:00',
  updateAt: '2023-02-08T18:31:14.000+00:00',
  deleted: 0,
  name: 'forsake',
  code: '000001',
  email: '1599868435@qq.com',
  phone: '1234567890',
  superiorCode: '',
};

const User: React.FC<BrowserRouterProps> = () => {
  const navigate = useNavigate();
  const chartData = useDynamicChart(visitData, {
    placeholderColor: ['legend.textStyle.color', 'xAxis.axisLabel.color', 'yAxis.axisLabel.color'],
  });
  const [user, setUser] = useState<IUser>(tempUser);

  /**
   * 获取用户信息
   */
  const getUserInfo = () => {
    (async () => {
      const token = localStorage.getItem('token');
      if (token == null || token === '') {
        navigate('/login');
        return;
      }

      const { data } = await axios.get('/api/admin/user/info', {
        headers: { token },
      });

      if (!data) {
        MessagePlugin.error('获取用户数据失败，请检查网络', 1500);
        return;
      }

      if (data.code === 0) {
        const { data: userInfo } = data;
        setUser({
          ...userInfo,
        });
      } else if (data.code === -1) {
        MessagePlugin.error('登录过期, 请重新登录', 1500);
        navigate('/login');
        localStorage.setItem('token', '');
      }
    })();
  };

  useEffect(() => {
    getUserInfo();
  }, []);

  const getDateNumber = () => {
    const date = Date.parse(user.createAt.split('T')[0]);
    const aDay = 1000 * 60 * 60 * 24;
    const res = (Date.now() - date) / aDay;
    return res.toFixed(0);
  };

  return (
    <div>
      <Row gutter={[16, 16]}>
        <Col xs={24} lg={24} xl={12}>
          <Card className={styles.welcome} bordered={false}>
            <Row justify='space-between'>
              <Col className={styles.name}>
                Hi, {user.name}{' '}
                <span className={styles.regular}>下午好，今天是你成为审核员的第 {getDateNumber()} 天～</span>
              </Col>
              <Col>
                <img alt='' src='https://tdesign.gtimg.com/starter/assets-tencent-logo.png' className={styles.logo} />
              </Col>
            </Row>
          </Card>
          <Card
            className={styles.userinfo}
            title='个人信息'
            bordered={false}
            actions={
              <Button shape='square' theme='default' variant='text'>
                <IconFont name='edit' />
              </Button>
            }
            header
          >
            <Row gutter={[16, 16]}>
              <Col span={3}>
                <div className={styles.label}>姓名</div>
                <div className={styles.value}>{user.name}</div>
              </Col>
              <Col span={3}>
                <div className={styles.label}>code</div>
                <div className={styles.value}>{user.code}</div>
              </Col>
              <Col span={3}>
                <div className={styles.label}>手机</div>
                <div className={styles.value}>+86 {user.phone}</div>
              </Col>
              <Col span={3}>
                <div className={styles.label}>邮箱</div>
                <div className={styles.value}>{user.email}</div>
              </Col>
            </Row>
            <Row gutter={[16, 16]}>
              <Col span={3}>
                <div className={styles.label}>加入时间</div>
                <div className={styles.value}>{user.createAt.split('T')[0]}</div>
              </Col>
            </Row>
          </Card>
          <Card className={styles.statistics} title='主页访问数据' subtitle='（次）' bordered={false}>
            <ReactEcharts option={chartData} notMerge={true} lazyUpdate={true} style={{ height: 360, marginTop: 16 }} />
          </Card>
        </Col>
      </Row>
    </div>
  );
};

export default memo(User);
