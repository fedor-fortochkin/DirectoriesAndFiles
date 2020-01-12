endpoint="/content/";
rootContentTable="#rootContentList"
dirContentTable='#dirContentList'


$(getRootContent())

$('#addDirBtn').click(function(){
  //jQuery.post( endpoint, $("#newDirectoryInput").val(),null,'text/plain',);
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
            $(rootContentTable).append('<tr><td>'+elem.date+'</td><td>'+elem.baseDir+'</td><td>'+elem.dirsCount+'</td><td>'+elem.filesCount+'</td><td>'+elem.contentSize+'</td><td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#dirContent" data-id="'+elem.id+'">Файлы</button></td></tr>');
        });
    })    
}

$('#dirContent').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var id = button.data('id');
    
    data = $.get(endpoint+id+"/",function(data){
        console.log(data);
        var modal = $(this);
        modal.find('.modal-title').text('New message to ' + id);
        console.log(modal.find('.modal-title'));
        $(dirContentTable+" tbody").empty();

        data.forEach(function(elem){
            $(dirContentTable).append('<tr><td>'+elem.name+'</td><td>'+(elem.size === null ? '&lt;DIR&gt;' : elem.size)+'</td>');
        });
    });    
});