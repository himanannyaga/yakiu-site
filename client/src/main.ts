//angular2に必要
import "core-js/es6";
import 'core-js/es7/reflect';
import "rxjs/add/operator/map";
import "zone.js/dist/zone";

//angular2
import {Component, Input, Output} from '@angular/core';
import {Http, HTTP_PROVIDERS, Headers} from '@angular/http';
import {bootstrap}    from '@angular/platform-browser-dynamic';

//
@Component({
  selector: 'my-app',
  template: require("./main.html")
})
export class AppComponent {}

bootstrap(AppComponent,[HTTP_PROVIDERS]);