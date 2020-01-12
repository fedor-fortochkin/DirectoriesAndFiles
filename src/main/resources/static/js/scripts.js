endpoint="/files";
rootContentTable="#rootContentList"
dirContentTable='#dirContentList'


$(getRootContent())

function addDirectory(dirname){
    
    
    jQuery.post( endpoint, $("#newDirectoryInput").value);
}

function getRootContent(){
    
    data = $.get(endpoint);
    data = [
        { 
          id: 1,
          date: "14.09.2016 18:03", 
          baseDir: "/opt/tomcat/temp",
          dirsCount: 12,
          filesCount: 251,
          contentSize: "128Mb"
        },
        { 
          id: 2,
          date: "14.09.2016 10:27", 
          baseDir: "/opt/tomcat/temp",
          dirsCount: 12,
          filesCount: 51,
          contentSize: "256Kb"
        }
    ];
    
    $(rootContentTable+" tbody").empty();
    
    data.forEach(function(elem){
        $(rootContentTable).append('<tr><td>'+elem.date+'</td><td>'+elem.baseDir+'</td><td>'+elem.dirsCount+'</td><td>'+elem.filesCount+'</td><td>'+elem.contentSize+'</td><td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#dirContent" data-id="'+elem.id+'">Файлы</button></td></tr>');
    });
    
}

$('#dirContent').on('show.bs.modal', function (event) {
    
    var button = $(event.relatedTarget);
    var id = button.data('id');
    
    
    //data = $.get(endpoint+"/"+dirId);
    data = [
        { 
          id: 1,
          name: "innerTemp",
          size: "&lt;DIR&gt;"
        },
        { 
          id: 2,
          name: "F1.txt",
          size: "12,57Kb"
        },
    ];
    
    var modal = $(this);
    modal.find('.modal-title').text('New message to ' + id);
    console.log(modal.find('.modal-title'));
    $(dirContentTable+" tbody").empty();
    
    data.forEach(function(elem){
        $(dirContentTable).append('<tr><td>'+elem.name+'</td><td>'+elem.size+'</td>');
    });
    
    
    
    
});