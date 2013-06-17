function LoadScript(url)
{
   var scripts = document.getElementsByTagName('script');
   for(var i=0; i<scripts.length; i++)
   {
      var src = scripts[i].getAttribute('src');
      if(src && src.toLowerCase() == url.toLowerCase())
      return;
   }
   
   document.write( '<scr' + 'ipt type="text/javascript" src="' + url + '"><\/scr' + 'ipt>' ) ;
}
function LoadCss(url)
{
   var links = document.getElementsByTagName('link');
   for(var i=0; i<links.length; i++)
   {
      var href = links[i].getAttribute('href');
      if(href && href.toLowerCase() == url.toLowerCase())
      return;
   }
   
	document.write( '<link href="' + url + '" type="text/css" rel="stylesheet" />' ) ;
}

LoadScript('/inc/js/jquery/jquery.min.js');
LoadScript('/inc/js/jquery/jquery-ui.custom.min.js');
LoadScript('/inc/js/jquery/jquery.cookie.js');
LoadScript('/inc/js/jquery/jquery.dynatree.min.js');


function Tree(id, jsonURL, iconsPath, checkbox, selectMode)
{
   this.id = id;
   this.jsonURL = jsonURL;
   this.iconsPath = iconsPath ? iconsPath : '/images/org/';
   this.checkbox = checkbox ? checkbox : false;
   this.selectMode = selectMode ? selectMode : 3;
   this.tree = null;
}

Tree.prototype.BuildTree = function()
{
   jQuery("#" + this.id).dynatree({
      minExpandLevel: 2,
      autoFocus: false,
      imagePath: this.iconsPath,
      checkbox: this.checkbox,
      selectMode: this.selectMode,
      fx: { height: "toggle", duration: 200 },
      initAjax: {
         url: this.jsonURL
      },
      onPostInit: function(isReloading, isError){
         if(typeof(tree_loaded) == 'function')
            tree_loaded(this, isReloading, isError);
      },
      onLazyRead: function(node){
         if(node.data.json)
         {
            node.appendAjax({
               url: node.data.json
            });
         }
         else
         {
            node.setLazyNodeStatus(DTNodeStatus_Ok)
         }
      },
      onActivate: function(node){
         if(typeof(tree_click) == 'function')
            tree_click(node);
      },
      onBlur: function(node) {
         node.deactivate();
      },
      onDblClick: function(node){
         if(typeof(tree_dblclick) == 'function')
            tree_dblclick(node);
      },
      onSelect: function(select, node){
         if(typeof(tree_select) == 'function')
            tree_select(select, node);
      },
      onRender: function(node, nodeSpan){
         if(typeof(tree_render) == 'function')
            tree_render(node, nodeSpan);
      },
      strings: {
         loading: "加载中...",
         loadError: "加载失败"
      }
   });
   
   this.tree = jQuery("#" + this.id).dynatree('getTree');
   
   jQuery("#" + this.id + " li").live('mouseenter', function(){
      var node = jQuery(this).attr("dtnode");
      if(!node.data.tooltip && node.data.show_ip)
      {
         jQuery.get('/ipanel/user/user_ip.php', {SHOW_IP:(node.data.show_ip ? '1' : ''), UID: node.data.uid}, function(data){
            node.data.tooltip = data;
            node.render();
         });
      }
   });
   
   jQuery("#" + this.id + " li").live('mouseleave', function(){
      var node = jQuery(this).attr("dtnode");
      if(node.data.op_sms)
      {
         jQuery("#" + node.data.key + '_op').hide();
      }
   });
}

Tree.prototype.addNode = function(data)
{
}

Tree.prototype.editNode = function(data)
{
   var node = this.tree.getNodeByKey(data.key);
   if(!node)
      return;
   
   if(data.new_key)
      node.data.key = data.new_key;
   if(data.icon)
      node.data.icon = data.icon;
   if(data.title)
      node.data.title = data.title;
   node.render();
}

Tree.prototype.deleteNode = function(data)
{
   var node = this.tree.getNodeByKey(data.key);
   if(!node)
      return;
   
   var parent = node.parent;
   node.remove();
   
   if(parent.countChildren() <= 0)
   {
      if(parent.parent == this.tree.getRoot())
         this.tree.reload();
      else
         parent.parent.reloadChildren();
   }
}

Tree.prototype.redrawNode = function(id,action,text,new_id)
{
   if(jQuery("#msg").length > 0)
      jQuery("#msg").hide();
   
   if(id=="0" || !id)
   {
      this.tree.reload();
      return;
   }
   
   if(action=="add" || action=="copy" || action=="cut" || action=="share")
   {
      var node = this.tree.getNodeByKey('folder_' + id);
      if(!node)
         return;
      
      if(node.data.isLazy)
      {
         node.reloadChildren();
      }
      else
      {
         node.parent.reloadChildren();
      }
   }
   else if(action=="delete")
   {
      this.deleteNode({key: 'folder_' + id});
   }
   else if(action=="rename")
   {
      this.editNode({key: 'folder_' + id, title: text, new_key: 'folder_' + new_id});
   }
}

function tree_click(node)
{
   if(typeof(window.top.ispirit_js) != 'function' || typeof(window.external.OA_SMS) == 'undefined')
   {
      if(node.data.url && node.data.target)
      {
         if(window.frames[node.data.target])
            window.frames[node.data.target].location = node.data.url;
         else if(parent.frames[node.data.target])
            parent.frames[node.data.target].location = node.data.url;
         else if(top.frames['table_index'] && top.frames['table_index'].frames[node.data.target])
            top.frames['table_index'].frames[node.data.target].location = node.data.url;
         else
            window.open(node.data.url);
      }
   }
   else if(typeof(node_click) == 'function')
   {
      node_click(node);
   }
}

function tree_select(select, node)
{
   if(node.data.onCheck)
      eval(node.data.onCheck + "('" + node.data.key + "', " + select + ");");
}

function get_client_type(client)
{
   if(client == "0")
      return "浏览器";
   else if(client == "1")
      return "手机浏览器";
   else if(client == "2")
      return "OA精灵";
   else if(client == "5")
      return "iPhone客户端";
   else if(client == "6")
      return "Android客户端";
   else
      return "";
}