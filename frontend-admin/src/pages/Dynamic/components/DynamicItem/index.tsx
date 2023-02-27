import { IDynamic } from 'modules/me/dynamic';
import React, { memo, useState } from 'react';
import { Card, Avatar, Space, Row, Col, Input, Button, MessagePlugin, Image } from 'tdesign-react';
import styles from './index.module.less';
import axios from 'axios';
import { IAuditVO } from 'modules/me/audit';

interface childProps {
  dynamic: IDynamic;
}

const DynamicItem: React.FC<childProps> = (props) => {
  const { dynamic } = props;
  const token = localStorage.getItem('token');

  // 审核记录
  const [item, setItem] = useState<IDynamic>(dynamic);
  // 审核意见
  const [message, setMessage] = useState<string>('');
  // 按钮状态
  const [passIsLoading, setPassIsLoading] = useState<boolean>(false);
  const [noPassIsLoading, setNoPassIsLoading] = useState<boolean>(false);

  /**
   * 更新按钮加载效果
   * @param value
   * @param flag
   */
  const setLoading = (value: boolean, flag: boolean) => {
    if (flag) setPassIsLoading(value);
    else setNoPassIsLoading(value);
  };

  /**
   * 提交审核
   * @param value
   */
  const auditHandler = async (value: boolean, itemVO: IDynamic) => {
    setLoading(true, value);

    const auditVO: IAuditVO = {
      id: itemVO.id,
      message,
      state: value === true ? 1 : -1,
    };

    const { data } = await axios.put('/api/audit/dynamic', { ...auditVO }, { headers: { token } });
    if (data.code !== 0) {
      MessagePlugin.error('审核失败: '.concat(data.msg), 1500);
      return;
    }
    MessagePlugin.info('审核成功', 1500);
    console.log(data);

    setLoading(false, value);

    setItem({
      ...itemVO,
      message,
    });

    setMessage('');
  };

  return (
    <Card
      key={item.id}
      title={item.nickName}
      description={item.createAt.split('T')[0]}
      bordered
      headerBordered
      hoverShadow
      avatar={<Avatar size='40px' image={item.avatarUrl}></Avatar>}
      style={{ width: '100%', marginBottom: '20px' }}
    >
      <div className={styles.title}>标题：{item.title}</div>
      <div className={styles.content}>内容：{item.content}</div>
      <div className={styles.image}>
        {item.dynamicFileList.map((file) => (
          <Space direction='vertical' key={file.id}>
            <Image src={file.path} fit='cover' style={{ width: 120, height: 120, marginRight: 20 }} />
          </Space>
        ))}
      </div>
      <div>
        {item.state !== 0 ? (
          <div>
            <Card
              bordered
              headerBordered={false}
              hoverShadow={false}
              loading={false}
              shadow={false}
              size='medium'
              theme='normal'
            >
              审核意见: {item.message}
            </Card>
          </div>
        ) : null}
        <Row className={styles.row}>
          <Col span={10} key={1}>
            <Input
              value={message}
              placeholder='请输入审核意见'
              onChange={(e) => {
                setMessage(e);
              }}
              clearable
              autoWidth={false}
            />
          </Col>
          <Col span={1} key={2}>
            <Button
              style={{ width: 100 }}
              theme='primary'
              variant='outline'
              disabled={false}
              loading={passIsLoading}
              onClick={() => auditHandler(true, item)}
            >
              √
            </Button>
          </Col>
          <Col span={1} key={3}>
            <Button
              style={{ width: 100 }}
              theme='danger'
              variant='outline'
              loading={noPassIsLoading}
              onClick={() => auditHandler(false, item)}
            >
              x
            </Button>
          </Col>
        </Row>
      </div>
    </Card>
  );
};

export default memo(DynamicItem);
