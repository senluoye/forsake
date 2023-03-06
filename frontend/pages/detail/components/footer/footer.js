// pages/detail/components/footer/footer.js
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
        comment: String,
    },

    /**
     * 组件的方法列表
     */
    methods: {
        openKeyBorad: function (e) {
            console.log(e)
        }
    },

    lifetimes: {
        ready: function () {
        },
    },
})
