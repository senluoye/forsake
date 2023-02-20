import Message from 'tdesign-miniprogram/message/index';

const showErrorMessage = (msg, that) => {
	Message.error({
		context: that,
		offset: [20, 32],
		duration: 3000,
		content: msg,
	});
}

const showTextMessage = (msg, that) => {
	Message.info({
		context: that,
		offset: [20, 32],
		duration: 3000,
		icon: false,
		content: msg,
	});
}

module.exports = {
	showErrorMessage, showTextMessage
}