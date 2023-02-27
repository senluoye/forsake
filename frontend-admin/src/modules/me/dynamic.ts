import { IFile } from './file';

export interface IDynamic {
  id: number;
  recordId: number;
  nickName: string;
  avatarUrl: string;
  title: string;
  createAt: string;
  updateAt: string;
  content: string;
  dynamicFileList: IFile[];
  state: number;
  message: string;
}
