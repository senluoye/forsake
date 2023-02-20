export interface IPage {
  current: number;
  size: number;
  total: number;
  [data: string]: any;
}

export interface IPageVO {
  currentPage: number;
  state: number;
}
