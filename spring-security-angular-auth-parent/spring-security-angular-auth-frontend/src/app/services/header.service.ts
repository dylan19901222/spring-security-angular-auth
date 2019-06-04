import { Headers } from '@angular/http';

export class HeaderService {
    /**
     * 取得驗證user的header
     */
    getHeader() {
        let headers = new Headers();
        headers.append('Accept', 'application/json')
        let base64Credential = localStorage.getItem('token');
        headers.append("Authorization", "Basic " + base64Credential);

        return headers;
    }
}
