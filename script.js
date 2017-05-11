function onNovoClick() {
    if (document.getElementById('test') == null){
        var test=document.createElement('section');
        test.style.width = "200px";
        test.setAttribute('id','test');

        var ul=document.createElement('ul');
        ul.classList.add('list-group');

        document.body.appendChild(test);
        test.appendChild(ul);

        for (var i=0; i<10; i++){
            console.log(i);
            var li=document.createElement('li');
            li.classList.add('list-group-item');

            ul.appendChild(li);
            li.innerHTML="Carro" + i;
        }
    }
}