webpackJsonp([1],{U693:function(e,t){},YVzU:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var l=a("Xxa5"),n=a.n(l),r=a("Dd8w"),s=a.n(r),i=a("exGp"),o=a.n(i),u={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("section",{staticClass:"filter"},[a("div",{staticClass:"filter-container"},[a("el-form",{ref:"form",attrs:{inline:!0}},[a("el-form-item",{attrs:{label:"文章名称："}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"请输入文章名称",clearable:""},on:{change:e.queryData},model:{value:e.title,callback:function(t){e.title=t},expression:"title"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"频道名称："}},[a("el-input",{staticClass:"filter-item",attrs:{placeholder:"请输入频道名称",clearable:""},on:{change:e.queryData},model:{value:e.channel_name,callback:function(t){e.channel_name=t},expression:"channel_name"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"状态："}},[a("el-select",{attrs:{placeholder:"请选择状态",clearable:""},model:{value:e.selectState,callback:function(t){e.selectState=t},expression:"selectState"}},e._l(e.stateList,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1)],1),e._v(" "),a("el-form-item",{attrs:{label:"发布日期："}},[a("el-date-picker",{staticClass:"el-date-picker",staticStyle:{width:"100%"},attrs:{type:"daterange",align:"right","unlink-panels":"","range-separator":"至","start-placeholder":"开始日期","end-placeholder":"结束日期","value-format":"yyyy-MM-dd"},on:{change:e.handlePublishDateChange},model:{value:e.publishDate,callback:function(t){e.publishDate=t},expression:"publishDate"}})],1),e._v(" "),a("el-form-item",{staticStyle:{float:"right"}},[a("el-button",{attrs:{type:"success",icon:"el-icon-search"},on:{click:e.queryData}},[e._v("查询")])],1)],1)],1)])},staticRenderFns:[]},c=a("VU/8")({props:["changeParam","addData"],data:function(){return{title:"",channel_name:"",selectState:"",stateList:[{label:"全部",value:""},{label:"正常",value:1},{label:"已下架",value:2}],publishDate:[]}},methods:{queryData:function(){var e=[];this.title&&e.push({filed:"title",type:"like",value:this.title}),this.channel_name&&e.push({filed:"channel_name",type:"like",value:this.channel_name}),this.changeParam(e)},handlePublishDateChange:function(e){}}},u,!1,null,null,null).exports,d=a("xT6B"),m=a("vLgD"),f=a("2EN7");function h(e){return Object(m.a)({url:f.b,method:"post",data:e,params:{}})}var p={props:["host","list","fileds","table","pageSize","total","changePage","changeStatus","editData","viewData"],data:function(){return{listPage:{currentPage:1},id:{filed:"id",type:"eq",value:""},setForStatus:{status:""}}},methods:{pageChange:function(e){this.changePage&&this.changePage({page:e})},dateFormat:function(e){return d.a.format13HH(e)},operateForDisable:function(e,t,a){var l=this;return o()(n.a.mark(function r(){var s,i;return n.a.wrap(function(n){for(;;)switch(n.prev=n.next){case 0:if(l.id.value=e,s={id:e},i=void 0,1!==t){n.next=9;break}return n.next=6,r=s,Object(m.a)({url:f.a,method:"post",data:r,params:{}});case 6:i=n.sent,n.next=12;break;case 9:return n.next=11,h(s);case 11:i=n.sent;case 12:200===i.code?(l.changeStatus(a,t),l.$message({type:"success",message:"操作成功！"})):l.$message({type:"error",message:i.errorMessage});case 13:case"end":return n.stop()}var r},r,l)}))()},operateForView:function(e){this.$router.push({path:"/content/detail",query:{id:e.id,title:e.title,author_name:e.author_name,publish_time:e.publish_time}})}}},b={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("section",{staticClass:"result"},[a("el-table",{attrs:{data:e.list,"header-cell-style":{textAlign:"center",fontWeight:"600"},"cell-style":{textAlign:"center"},"highlight-current-row":""}},[a("el-table-column",{attrs:{label:"序号",type:"index",width:"50"}}),e._v(" "),a("el-table-column",{attrs:{label:"文章名称"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.title))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"作者"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.author_name))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"所属频道"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.channel_name))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"评论数"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.collection))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"更新时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",{domProps:{innerHTML:e._s(e.dateFormat(t.row.publish_time).split(" ").join("<br/>"))}})]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(0===t.row.status?"正常":"已下架"))])]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"操作",width:"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{staticClass:"el-button--success-text",attrs:{type:"text"},on:{click:function(a){return e.operateForView(t.row)}}},[e._v("查看")]),e._v(" "),a("el-button",{staticClass:"el-button--primary-text",attrs:{type:"text",disabled:0===t.row.status},on:{click:function(a){return e.operateForDisable(t.row.id,0,t.$index)}}},[e._v("上架")]),e._v(" "),a("el-button",{staticClass:"el-button--danger-text",attrs:{type:"text",disabled:1===t.row.status},on:{click:function(a){return e.operateForDisable(t.row.id,1,t.$index)}}},[e._v("下架")])]}}])})],1)],1),e._v(" "),a("el-pagination",{attrs:{layout:"total, sizes, prev, pager, next, jumper","current-page":e.listPage.currentPage,"page-size":e.pageSize,total:e.total},on:{"current-change":e.pageChange,"update:currentPage":function(t){return e.$set(e.listPage,"currentPage",t)},"update:current-page":function(t){return e.$set(e.listPage,"currentPage",t)}}})],1)},staticRenderFns:[]},v=a("VU/8")(p,b,!1,null,null,null).exports,g=a("t5DY"),_={name:"commn-editor",props:["title","fileds","table","submitSuccess"],data:function(){return{disable:!1,model:"add",dialogFormVisible:!1,formLabelWidth:"80px",entry:{},form:{},rules:{}}},mounted:function(){this.refresh()},methods:{add:function(){this.dialogFormVisible=!0,this.entry={},this.model="add",this.refresh()},edit:function(e){this.dialogFormVisible=!0,this.entry=e,this.model="edit",this.refresh()},view:function(e){this.disable=!0,this.dialogFormVisible=!0,this.entry=e,this.model="view",this.refresh()},getTitle:function(){return("add"==this.model?"新增":"view"==this.model?"查看":"修改")+" - "+this.title},refresh:function(){for(var e=0;e<this.fileds.length;e++){var t=this.fileds[e];t.rule&&(this.rules[t.name]=t.rule);var a=t.value;this.entry&&void 0!=this.entry[t.name]&&(a=this.entry[t.name]),"boolean"==typeof a&&(a=a?1:0),this.$set(this.form,t.name,a)}},submit:function(){var e=this;this.$refs.commForm.validate(function(t){if(!t)return!1;var a=[];for(var l in e.form)e.form[l]&&("add"==e.model||"edit"==e.model&&-1==l.indexOf("_time"))&&a.push({filed:l,value:e.form[l]});var n={model:e.model,name:e.table,where:[{filed:"id",type:"eq",value:e.entry.id}],sets:a};e.submitToBack(n)})},submitToBack:function(e){var t=this;return o()(n.a.mark(function a(){var l;return n.a.wrap(function(a){for(;;)switch(a.prev=a.next){case 0:return a.next=2,Object(g.b)(e);case 2:0==(l=a.sent).code?(t.dialogFormVisible=!1,t.submitSuccess(),t.$message({type:"success",message:t.getTitle()+"操作成功！"})):t.$message({type:"error",message:l.error_message});case 4:case"end":return a.stop()}},a,t)}))()}}},y={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("el-dialog",{ref:"dialog",attrs:{title:e.getTitle(),visible:e.dialogFormVisible},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"commForm",attrs:{model:e.form,rules:e.rules}},[e._l(e.fileds,function(t){return["input"==t.type?a("el-form-item",{attrs:{label:t.label,"label-width":e.formLabelWidth,prop:t.name}},[a("el-input",{attrs:{disabled:e.disable,value:t.value,autocomplete:"off",placeholder:t.placeholder},model:{value:e.form[t.name],callback:function(a){e.$set(e.form,t.name,a)},expression:"form[item.name]"}})],1):e._e(),e._v(" "),"number"==t.type?a("el-form-item",{attrs:{label:t.label,"label-width":e.formLabelWidth,prop:t.name}},[a("el-input-number",{attrs:{disabled:e.disable,step:1,value:t.value},model:{value:e.form[t.name],callback:function(a){e.$set(e.form,t.name,a)},expression:"form[item.name]"}})],1):e._e(),e._v(" "),"select"==t.type?a("el-form-item",{attrs:{label:t.label,"label-width":e.formLabelWidth,prop:t.name}},[a("el-select",{attrs:{disabled:e.disable,placeholder:t.placeholder,value:t.value},model:{value:e.form[t.name],callback:function(a){e.$set(e.form,t.name,a)},expression:"form[item.name]"}},e._l(t.options,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}),1)],1):e._e(),e._v(" "),"radio"==t.type?a("el-form-item",{attrs:{label:t.label,"label-width":e.formLabelWidth,prop:t.name}},[a("el-radio-group",{attrs:{disabled:e.disable,value:t.value},model:{value:e.form[t.name],callback:function(a){e.$set(e.form,t.name,a)},expression:"form[item.name]"}},e._l(t.radios,function(t){return a("el-radio",{key:t.value,attrs:{label:t.value}},[e._v(e._s(t.label))])}),1)],1):e._e()]})],2),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[e.disable?a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("关 闭")]):e._e(),e._v(" "),e.disable?e._e():a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取 消")]),e._v(" "),e.disable?e._e():a("el-button",{attrs:{type:"primary"},on:{click:e.submit}},[e._v("确 定")])],1)],1)},staticRenderFns:[]};var w={name:"ChannelManager",data:function(){return{params:{name:"AP_ARTICLE",page:1,size:10,where:[]},total:0,host:"",list:[],fileds:[{list:!0,label:"标题",name:"title",type:"input",placeholder:"请输入标题",rule:[{required:!0,message:"请输入标题",trigger:"blur"},{min:10,max:20,message:"标题在10~50个字符",trigger:"blur"}]},{list:!0,label:"作者",name:"author_name",type:"input"},{list:!0,label:"频道",name:"channel_name",type:"input"},{list:!0,label:"点赞数",name:"likes",type:"input"},{list:!0,label:"收藏数",name:"collection",type:"input"},{list:!0,label:"评论数",name:"collection",type:"input"},{list:!0,label:"阅读数",name:"views",type:"input"},{list:!0,label:"创建时间",name:"created_time",type:"hidden",value:d.a.format13HH((new Date).getTime())},{list:!0,label:"发布时间",name:"publish_time",type:"hidden",value:d.a.format13HH((new Date).getTime())}]}},mounted:function(){this.loadData()},components:{SearchTool:c,SearchResult:v,Editor:a("VU/8")(_,y,!1,function(e){a("rgHl")},"data-v-2f512a2f",null).exports},methods:{viewData:function(e){this.$refs.editor.view(e)},addData:function(e){this.$refs.editor.add()},submitSuccess:function(){this.loadData()},changeStatus:function(e,t){this.$set(this.list[e],"status",t)},changeParam:function(e){this.params.page=1,this.params.where=e,this.loadData()},changePage:function(e){this.params.page=e.page,this.loadData()},loadData:function(){var e=this;return o()(n.a.mark(function t(){var a;return n.a.wrap(function(t){for(;;)switch(t.prev=t.next){case 0:return t.next=2,Object(g.a)(s()({},e.params));case 2:200===(a=t.sent).code?(e.list=a.data.list,e.host=a.host,e.total=a.data.total):e.$message({type:"error",message:a.errorMessage});case 4:case"end":return t.stop()}},t,e)}))()}}},k={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("Editor",{ref:"editor",attrs:{fileds:e.fileds,title:"内容",table:this.params.name,submitSuccess:e.submitSuccess}}),e._v(" "),a("search-tool",{attrs:{changeParam:e.changeParam,addData:e.addData}}),e._v(" "),a("search-result",{ref:"mySearchResult",attrs:{list:e.list,host:e.host,total:e.total,table:this.params.name,viewData:e.viewData,changePage:e.changePage,changeStatus:e.changeStatus,fileds:e.fileds,pageSize:e.params.size}})],1)},staticRenderFns:[]};var x=a("VU/8")(w,k,!1,function(e){a("U693")},"data-v-7aad81ea",null);t.default=x.exports},rgHl:function(e,t){},t5DY:function(e,t,a){"use strict";t.a=function(e){return new l.a({url:n.k,method:"post",data:e})},t.b=function(e){return new l.a({url:n.l,method:"post",data:e})};var l=a("vLgD"),n=a("2EN7")}});
//# sourceMappingURL=1.296ea948914b3024f9f0.js.map