import axios from 'axios'

import vars from '../variables'

export function checkLogin () {
    let loginUrl = vars.baseUrl + '/oauth/authorize?response_type=token&client_id=the-client&redirect_uri=http://localhost:3000&scope=read'
    let token = localStorage.token
    if (window.location.hash.match('access_token')) {
        token = window.location.hash.match(/\#(?:access_token)\=([\S\s]*?)\&/)[1]
    }
    if (token) {
        return axios.get(vars.baseUrl + '/api/me?access_token='+token)
        .then(d => {
            localStorage.token = token
        })
        .catch(e => {
            window.location.replace(loginUrl);
        })
    }
    window.location.replace(loginUrl);
    return Promise.reject()
}

export default class User {
    // login
}