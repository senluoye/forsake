import React, { memo, useEffect, useState } from 'react';
import { MessagePlugin, Pagination, Select, Loading } from 'tdesign-react';
import { IPage, IPageVO } from 'modules/global/page';
import CommonStyle from 'styles/common.module.less';
import classnames from 'classnames';

import styles from './index.module.less';
import axios from 'axios';
import { BrowserRouterProps, useNavigate } from 'react-router-dom';
import { IDynamic } from 'modules/me/dynamic';
import DynamicItem from './components/DynamicItem';

const tempPageContent: IPage = {
  data: [],
  current: 0,
  total: 0,
  size: 20,
};

const tempPageVO: IPageVO = {
  currentPage: 0,
  state: 0,
};

const tempDynamicList: IDynamic[] = [
  {
    id: 0,
    recordId: 0,
    nickName: '',
    avatarUrl: '',
    title: '',
    createAt: '',
    updateAt: '',
    content: '',
    dynamicFileList: [],
    state: 0,
    message: '',
  },
];

const Dynamic: React.FC<BrowserRouterProps> = () => {
  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  // 动态列表
  const [dynamicList, setDynamicList] = useState<IDynamic[]>(tempDynamicList);
  // 分页实体
  const [pageContent, setPageContent] = useState<IPage>(tempPageContent);
  // 分页VO
  const [pageVO, setPageVO] = useState<IPageVO>(tempPageVO);
  // 是否加载
  const [isLoading, setIsLoading] = useState<boolean>(false);
  // 选择器的值
  const [selectValue, setSelectValue] = useState<number>(0);

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

  /**
   * 修改pagevo
   */
  const setNextPage = (page?: number) => {
    if (page) setPageVO({ ...pageVO, currentPage: pageVO.currentPage + page });
    else setPageVO({ ...pageVO, currentPage: pageVO.currentPage + 1 });
  };

  /**
   * 获取下一页的数据
   * @returns
   */
  const getNextPageContent = (page?: IPageVO) => {
    (async () => {
      if (!checkToken) return;

      setIsLoading(true);

      const tPage = page || pageVO;
      const { data } = await axios.post('/api/audit/list/dynamic', { ...tPage }, { headers: { token } });
      if (!data) {
        MessagePlugin.error('获取用户数据失败，请检查网络', 1500);
        return;
      }
      console.log(data);

      switch (data.code) {
        case -1: {
          MessagePlugin.error('错误: '.concat(data.msg), 1500);
          break;
        }
        case -2: {
          MessagePlugin.error('登录过期, 请重新登录', 1500);
          navigate('/login');
          localStorage.setItem('token', '');
          break;
        }
        default: {
          const page: IPage = data.data;
          setPageContent({ ...page });
          const dataList: IDynamic[] = page.data;
          setDynamicList(() => {
            const res: IDynamic[] = [];
            dataList.map((item) => res.push(item));
            return res;
          });
          break;
        }
      }

      // 设置当前是否可评审的状态列表
      const stateMap: Map<number, boolean> = new Map();
      for (let i = 0; i < dynamicList.length; i++) {
        stateMap.set(dynamicList[i].id, false);
      }

      setIsLoading(false);
    })();
  };

  const onChange = React.useCallback((pageInfo) => {
    const index = pageContent.current;
    setNextPage(pageInfo);
    console.log(`current: ${index}`);
  }, []);

  const onPageSizeChange = React.useCallback((index, pageInfo) => {
    // setPageSize(index);
    console.log(`pageSize: ${index}`);
    console.log(`pageInfo: ${JSON.stringify(pageInfo)}`);
  }, []);

  /**
   * 修改查询范围
   * @param value
   */
  const changeSelect = (state: any) => {
    setSelectValue(state);
    const tPage = { currentPage: 0, state };
    console.log(tPage);
    getNextPageContent(tPage);
  };

  useEffect(() => {
    getNextPageContent();
  }, []);

  return (
    <div className={classnames(CommonStyle.pageWithPadding, CommonStyle.pageWithColor)}>
      <Select
        value={selectValue}
        onChange={changeSelect}
        style={{ width: '10%', marginLeft: '85%' }}
        options={[
          { label: '全部', value: 0 },
          { label: '未审核', value: -11 },
          { label: '已审核', value: 11 },
          { label: '已通过', value: 1 },
          { label: '未通过', value: -1 },
        ]}
        className={styles.select}
      />
      {dynamicList === undefined ? (
        <div>
          {isLoading ? (
            <Loading
              delay={0}
              fullscreen={false}
              indicator
              inheritColor={false}
              loading
              preventScrollThrough
              showOverlay
              size='medium'
              style={{ margin: '0 auto', marginLeft: '45%' }}
            />
          ) : (
            <div>没有数据</div>
          )}
        </div>
      ) : (
        <div>
          {isLoading ? (
            <Loading
              delay={0}
              fullscreen={false}
              indicator
              inheritColor={false}
              loading
              preventScrollThrough
              showOverlay
              size='medium'
              style={{ margin: '0 auto', marginLeft: '45%' }}
            />
          ) : (
            <div>
              {dynamicList.length === 0 ? (
                <div className={styles.noData}>没有数据</div>
              ) : (
                <div>
                  {dynamicList.map((item) => (
                    <DynamicItem key={item.id} dynamic={item} />
                  ))}
                </div>
              )}
            </div>
          )}
          <Pagination
            className={styles.page}
            total={pageContent.total}
            current={pageContent.current + 1}
            pageSize={pageContent.size}
            pageSizeOptions={[20]}
            onChange={onChange}
            onPageSizeChange={onPageSizeChange}
          />
        </div>
      )}
    </div>
  );
};

export default memo(Dynamic);
