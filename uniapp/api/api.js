const BASE_URL = 'http://localhost:8081';

//jwt
export const http = (options) => {
    return new Promise((resolve, reject) => {
        uni.request({
            url: BASE_URL + options.url,
            method: options.method || 'GET',
            data: options.data || {},
            header: { Authorization: uni.getStorageSync('token') },
            success: (res) => {
                debugger
                if (res == '') {
                    return uni.showToast({
                        icon: 'loading',
                        title: '获取数据失败'
                    })
                }
                resolve(res)
            },
            fail: (err) => {
                return uni.showToast({
                    icon: 'loading',
                    title: '请求失败'
                })
                reject(err)
            }
        })
    })
}
