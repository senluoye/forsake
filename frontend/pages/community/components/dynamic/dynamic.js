import Message from 'tdesign-miniprogram/message/index';
import { showErrorMessage, showTextMessage } from "../../../../utils/feedback";
import { myRequest } from "../../../../utils/sendUtil";

Component({
    /**
     * 组件的属性列表
     */
    properties: {
        dynamic: Object,
    },

    /**
     * 组件的初始数据
     */
    data: {
    },

    /**
     * 组件的方法列表
     */
    methods: {
        checkToken: (token) => {
            if (token === null || token === "") {
                showErrorMessage("还未登陆，请登录再进行操作", this)
                return
            }
        },

        goDetail(e) {
            const id = this.data.dynamic.id
            wx.navigateTo({
                url: '/pages/detail/detail?id=' + id,
            })
        },

        /**
         * 点赞
         */
        async star() {
            const token = wx.getStorageSync('token')
            this.checkToken(token)

            let dynamicId = this.data.dynamic.id
            const res = await myRequest.postDynamic('/api/dynamic/star', dynamicId, token)
                .catch((e) => { return {} })
            const { data: dynamicStarVO } = res
            console.log(dynamicStarVO)
            if (JSON.stringify(res) == '{}' || res.code !== 0 || res === null) {
                showErrorMessage(res.msg === undefined ? "发生错误" : res.msg, this)
                return
            }

            this.setData({
                dynamic: {
                    ...this.data.dynamic,
                    likeCount: dynamicStarVO.likeCount,
                    isLike: !this.data.dynamic.isLike
                },
            })
        },

        async comment() {

        }
    },

    // 生命周期
    lifetimes: {
        ready: function () {
            console.log(this.data.dynamic)
            let now = this.data.dynamic
            let updateAt = now.updateAt === "" ? now.createAt : now.updateAt
            updateAt = now.updateAt = now.updateAt.split('T')[0]
            this.setData({
                dynamic: {
                    ...this.data.dynamic,
                    updateAt: updateAt,
                }
            })

            // 简化内容
            let contentList = now.content.split('\n')
            if (contentList.length >= 3) {
                let content = contentList[0] + '\n' + contentList[1] + '\n' + contentList[2]
                    + '...';
                this.setData({
                    dynamic: {
                        ...this.data.dynamic,
                        content: content,
                    }
                })
            }
        },

        // 在组件实例进入页面节点树时执行
        attached: function () {
        },

        // 在组件实例被从页面节点树移除时执行
        detached: function () {
        },
    },

    pageLifetimes: {
        // 页面被展示
        show: function () {
        },

        // 页面被隐藏
        hide: function () {
        },

        // 页面尺寸变化
        resize: function (size) {
        }

    }
})
