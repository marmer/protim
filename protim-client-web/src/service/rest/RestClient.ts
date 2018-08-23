import {HttpStatus} from "./HttpStatus";
import {XhrReadyState} from "./XhrReadyState";

export class RestClient {
    public static getJson(url: string): Promise<any> {
        return new Promise<string>((resolve, reject) => {
            const xhr = new XMLHttpRequest();
            xhr.onreadystatechange = () => {
                if (xhr.readyState === XhrReadyState.DONE && xhr.status === HttpStatus.OK) {
                    try {
                        resolve(JSON.parse(xhr.responseText))
                    } catch (e) {
                        reject()
                    }
                }
            };
            xhr.open("GET", url, true);
            xhr.withCredentials = true; // TODO: marmer 16.08.2018 remove credentials again from here!
            xhr.setRequestHeader('Accept', 'application/JSON');
            xhr.send(/*body*/);
        });
    }
}