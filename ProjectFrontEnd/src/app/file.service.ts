import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FileService {

  constructor(private http:HttpClient) { }

  public addFiles(id:any, name:string, data:FormData) {
    data.append('name',name);
    data.append('id', id);
    return this.http.post('http://localhost:8080/app/file/add-file',data, { responseType : "text"});
  }

  public allVersions(id:number) {
    return this.http.get('http://localhost:8080/app/file/all-versions/'+id);
  }

  public getMaterialByCId(id:number) {
    return this.http.get('http://localhost:8080/app/file/get-material-cid/'+id);
  }

  ToArrayBuffer(base64:any) {
    const binaryString = window.atob(base64);
    const len = binaryString.length;
    const bytes = new Uint8Array(len);

    for (let i = 0; i < len; i++) {
      bytes[i] = binaryString.charCodeAt(i);
    }

    return bytes.buffer;
  }

  downloadMaterial(data:any, type:string) {
    const byteArray = this.ToArrayBuffer(data);
    const blob = new Blob([byteArray], { type: type });
    const url = URL.createObjectURL(blob);
    window.open(url);
  }

  deleteVersion(id:number) {
    return this.http.get('http://localhost:8080/app/file/delete-version/'+id, {responseType : 'text'});
  }
}
