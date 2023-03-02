const local = "http://localhost:8081"
const forsake = "http://forsake.cn:8081"
const userUrl = forsake
const dynamicUrl = forsake
const ossUrl = forsake

const basePost = async (baseUrl, path, data, token) => {
	const url = baseUrl + path
	return wx.p.request({
		url: url,
		method: 'POST',
		header: {
			'content-type': 'application/json',
			'flag': 'flag',
			'token': token,
		},
		data: data,
	}).then(data => data.data)
}

const baseFormPost = async (baseUrl, path, data, token) => {
	const url = baseUrl + path
	return wx.uploadFile({
		filePath: 'filePath',
		name: 'name',
		url: 'url',
	})
	return wx.p.request({
		url: url,
		method: 'POST',
		header: {
			'content-type': 'multipart/form-data',
			'flag': 'flag',
			'token': token,
		},
		data: data,
	}).then(data => data.data)
}

const baseGet = async (baseUrl, path, token) => {
	const url = baseUrl + path
	return wx.p.request({
		url: url,
		method: 'GET',
		header: {
			'content-type': 'application/json',
			'flag': 'flag',
			'token': token,
		},
	}).then(data => data.data)
}

const basePut = async (baseUrl, path, data, token) => {
	const url = baseUrl + path
	return wx.p.request({
		url: url,
		method: 'PUT',
		header: {
			'content-type': 'application/json',
			'flag': 'flag',
			'token': token,
		},
		data: data,
	}).then(data => data.data)
}

const myRequest = {

	// User
	postUser: async (path, data, token) => {
		return basePost(userUrl, path, data, token)
	},

	getUser: async (path, token) => {
		return baseGet(userUrl, path, token)
	},

	putUser: async (path, data, token) => {
		return basePut(userUrl, path, data, token)
	},

	// Dynamic
	postDynamic: async (path, data, token) => {
		return basePost(dynamicUrl, path, data, token)
	},

	getDynamic: async (path, token) => {
		return baseGet(dynamicUrl, path, token)
	},

	putDynamic: async (path, data, token) => {
		return basePut(dynamicUrl, path, data, token)
	},

	// OSS
	postOss: async (path, data, token) => {
		return basePost(ossUrl, path, data, token)
	},

	getOss: async (path, token) => {
		return baseGet(ossUrl, path, token)
	},
}

module.exports = {
	myRequest
}