export const CREDENTIALS_COOKIE = 'storedCredentials';
export const TOKEN_COOKIE = 'token';

var url = new URL(window.location.origin);
url.port = '';
export const API_HOST= window.location.hostname === 'localhost' ? 'http://192.168.0.121:30001' : url.toString().replace(/\/$/, "") + ':30001';
//export const API_HOST = 'http://localhost:8080'