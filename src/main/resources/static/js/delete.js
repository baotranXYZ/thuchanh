function deleteBook(link){
       if(confirm('Are you sure')){
       $.ajax({
           url: $(link).attr('href'),
           type: 'DELETE',
           success: result =>{
             if(!result.success){
               alert(result.message);
             }else{
               $(link).parent().parent().remove();
             }
           }
       });
       }

     }