import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FileService } from '../file.service';
import { LoginService } from '../login.service';
import { User } from '../user';
import { Location } from '@angular/common'
import { HttpEventType } from '@angular/common/http';

@Component({
  selector: 'app-add-files',
  templateUrl: './add-files.component.html',
  styleUrls: ['./add-files.component.css']
})
export class AddFilesComponent implements OnInit {

  fileName = '';
  user : User;
  cid : number;
  message : string;
  file ?: File;

  formData : FormData = new FormData();
  flag : boolean = false;

  ngOnInit(): void {
  }

  constructor(private activatedroute:ActivatedRoute,
              private fileService:FileService,
              private loginService:LoginService,
              private router:Router,
              private location:Location) { 
                this.user = this.loginService.getWithExpiry('user');
                if(!this.user) {
                  this.router.navigate([`/login`]);
                }

                this.cid = Number(this.activatedroute.snapshot.paramMap.get('id'));
              }

  onFileSelected(event: any) {
      
      this.file = (event.target.files as FileList)[0];

      if (this.file) {
        
        this.fileName = this.file.name;
        
        this.formData.append('data',this.file);
      }
  }

  submit() {
    if(this.file) {
      this.flag = true;
      this.fileService.addFiles(this.cid, this.fileName,this.formData).subscribe((response:any) => {
        this.message = response,
        alert(this.message);
        window.location.reload();
        this.flag = false;
  })
 } else {
    alert("select a file to upload");
  }
  }

  goBack() {
    this.location.back();
  }

//   .subscribe((response:any) => {
//     this.message = response;
//     alert(this.message),
//     window.location.reload();
//   })
// } else {
//   alert("select a file to upload");
// }

}
