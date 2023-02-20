// custom-tab-bar/index.js
Component({
    /**
     * 组件的属性列表
     */
    properties: {

    },

    /**
     * 组件的初始数据
     */
    data: {
        // 当前选中的item
        value: '0',

        // TabBar的基础数据
        list: [{
            "pagePath": "/pages/community/community",
            "text": "首页",
            "iconPath": "/static/image/发现.png",
            "selectedIconPath": "/static/image/发现1.png"
        },
        {
            "pagePath": "/pages/follow/follow",
            "text": "关注",
            "iconPath": "/static/image/关注.png",
            "selectedIconPath": "/static/image/关注1.png"
        },
        {
            "pagePath": "/pages/me/me",
            "text": "我的",
            "iconPath": "/static/image/我的.png",
            "selectedIconPath": "/static/image/我的1.png"
        }],
    },

    /**
     * 组件的方法列表
     */
    methods: {
        onChange(e) {
            let value = e.detail.value;
            let url = this.data.list[value].pagePath;
            // this.setData({ value });
            wx.switchTab({ url });
        },
    },
})
