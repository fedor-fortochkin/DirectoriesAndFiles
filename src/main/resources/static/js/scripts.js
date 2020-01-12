endpoint="/content/";
rootContentTable="#rootContentList"
dirContentTable='#dirContentList'

function fileSizeSI(a,b,c,d,e){
 return (b=Math,c=b.log,d=1e3,e=c(a)/c(d)|0,a/b.pow(d,e)).toFixed(2)
 +' '+(e?'KMGTPEZY'[--e]+'b':'Bytes')
}

$(getRootContent())

$('#addDirBtn').click(function(){
  $.ajax({
    url:endpoint,
    type:"POST",
    data:$("#newDirectoryInput").val(),
    contentType:"text/plain; charset=utf-8",
    dataType:"text",
    async:false
  });
    
    getRootContent();
});

function getRootContent(){
    data = $.get(endpoint,function(data) {
        console.log(data);
        $(rootContentTable+" tbody").empty();
        data.forEach(function(elem){
            $(rootContentTable).append('<tr><td>'+elem.date+'</td><td>'+elem.baseDir+'</td><td>'+elem.dirsCount+'</td><td>'+elem.filesCount+'</td><td>'+fileSizeSI(elem.contentSize)+'</td><td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#dirContent" data-id="'+elem.id+'" data-title="'+elem.baseDir+' '+elem.date+'">Файлы</button></td></tr>');
        });
    })    
}

$('#dirContent').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var id = button.data('id');
    var title = button.data('title');
    data = $.get(endpoint+id+"/",function(data){

        $('#modal-title').text(title);
        
        $(dirContentTable+" tbody").empty();

        data.forEach(function(elem){
            $(dirContentTable).append('<tr><td>'+elem.name+'</td><td>'+(elem.size === null ? '&lt;DIR&gt;' : fileSizeSI(elem.size))+'</td>');
        });
    });    
});