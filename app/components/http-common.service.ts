import {Http, Response, Headers, RequestOptions,URLSearchParams} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {RequestParameters} from './request.params';

@Injectable()
export class HttpServies {
    constructor(private http: Http) { }

    //private _url:string='http://192.168.1.122:8080/';
    private _url:string='http://angularattack2016abmv.herokuapp.com/';

    public callSearch(path:string,passedParams:Array<RequestParameters>){
        let params = new URLSearchParams();
        if(passedParams!=null){
        passedParams.forEach(e =>{
            params.set(e.key,e.value);
        }) ;}
        return this.http.get(this._url+path,{search:params}).map(this.extractData).catch(this.handleError);
    }
    
    public callSave(path:string,body:string){
       /* let header:Headers=new Headers();
        header.set('Content-Type','application/json');
        return this.http.post(this._url+path,body,{headers:header}).map(this.extractData).catch(this.handleError);*/
        return this.http.post(this._url+path,body).map(this.extractData).catch(this.handleError);
    }   
    
    public callDelete(path:string,body:string){
        let header:Headers=new Headers();
        header.set('Content-Type','application/json');
        return this.http.post(this._url+path,body,{headers:header}).map(this.extractData).catch(this.handleError);
    } 

    private extractData(res: Response) {
        if (res.status < 200 || res.status >= 300) {
            throw new Error('Response status: ' + res.status);
        }
        let body = res.json();
        console.log(body);
        return body;
    }
    private handleError(error: any) {
        let errMsg = error.message || 'Server error';
        console.error(errMsg);
        return Observable.throw(errMsg);
    }

}