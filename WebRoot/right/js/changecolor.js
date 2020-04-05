	var rows=document.getElementsByTagName('tr');
	for (var i=0;i<rows.length;i++){
			rows[i].onmouseover=function(){
				this.className='hover';
			}
			rows[i].onmouseout=function(){
				this.className='';
			}
		}
	var rowsth=document.getElementsByTagName('th');
	for (var i=0;i<rowsth.length;i++){
			rowsth[i].onmouseover=function(){
				this.className='hover';
			}
			rowsth[i].onmouseout=function(){
				this.className='';
			}
		}