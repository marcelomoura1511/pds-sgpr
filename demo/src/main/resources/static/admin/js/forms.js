//0 = add form
//1 = update form
var selectForm = 0;

function createFormUpdateDelete(){
    selectForm = 1;
    clearForm();
    document.getElementById('mainForm').className = 'forms ' + "formUp" + page;

    if(page !== 31){
        switch(page){
            case 1:
                createHeadForm('Atualizar Usuario');
                break;
            case 2:
                createHeadForm('Atualizar Onibus');
                break;
            case 3:
                createHeadForm('Atualizar Rota');
                break;
            case 4:
                createHeadForm('Atualizar Viagem');
                break;
            case 5:
                createHeadForm('Atualizar Motorista');
                break;
            case 6:
                createHeadForm('Atualizar Cidade');
                break;
        }
        
        if(page == 4){
            let atr = Object.keys(globalResouces.main[0]);
            createINnputForms(atr.slice(3, atr.length));    
        }else{
            createINnputForms(Object.keys(globalResouces.main[0]));
        }
        createBtnsUpdateForm();
    }else{
        createUpdateCitytoRoute(arguments[0]);
    }
}

function createAddForm(listOfAtributes, tableName){
    document.getElementById('mainForm').className = 'forms ' + "formAdd" + page;
    createHeadForm('Adicionar Novo ' + tableName);
    createINnputForms(listOfAtributes);
    createBtnsAddForm();
}

function createHeadForm(nameForm){
    let h2 = document.createElement('H2');
    h2.innerText = nameForm;
    document.getElementById('mainForm').appendChild(h2);
}

function createINnputForms(namesForInput){
    let btnInputPrefix = selectForm ? "heightBtnInputUp" : "heightBtnInputAdd";
    let form = document.getElementById('mainForm');

    for(let prop of namesForInput){
        let input = document.createElement('INPUT');
        input.type = 'text';
        input.placeholder = prop;
        input.className = 'formsInput ' + btnInputPrefix + page;
        form.appendChild(input);
    }
}

function createBtnsAddForm(){
    let classBtnAdd = 'formsButton confirBtn ' + 'heightBtnInputAdd' + page;
    let btn = createButton('Adicionar', classBtnAdd);
    btn.addEventListener('click', addResource);

    document.getElementById('mainForm').appendChild(btn);
    createCancelButton();
}

function createBtnsUpdateForm(){
    let classBtnUp = 'formsButton confirBtn ' + 'heightBtnInputUp' + page;
    let classBtnDel = 'formsButton deleteBtn ' + 'heightBtnInputUp' + page;

    document.getElementById('mainForm').appendChild(createButton('Atualizar', classBtnUp));
    document.getElementById('mainForm').appendChild(createButton('Deletar', classBtnDel));
    createCancelButton();
}

function createButton(name, classForTheButton){
    let btn = document.createElement('BUTTON');

    btn.innerText = name;

    btn.type = 'button';

    btn.className = classForTheButton;

    return btn;
}

function createCancelButton(){
    let btnInputPrefix = selectForm ? "heightBtnInputUp" : "heightBtnInputAdd";
    let btnCancel = document.createElement('BUTTON');

    btnCancel.innerText = 'Cancelar';

    btnCancel.type = 'button';

    btnCancel.className = 'formsButton ' + btnInputPrefix + page;

    btnCancel.addEventListener('click', hideForm);

    document.getElementById('mainForm').appendChild(btnCancel);
}

function clearForm(){
    document.getElementById('mainForm').innerHTML = '';
}

function createAddCitytoRoute(){
    document.getElementById('mainForm').className = 'forms ' + "formAdd" + page;
    createHeadForm("Adicionar Cidade a Rota");
    createINnputForms(["id_cidade, id_cidade, ..., id_cidade", "nome_cidade, nome_cidade, ..., nome_cidade"]);
    createBtnsAddForm();
}

function createUpdateCitytoRoute(cityName){
    let classBtnDel = 'formsButton deleteBtn ' + 'heightBtnInputUp' + page;

    createHeadForm('Atualizar Rota');
    createINnputForms([cityName]);
    document.getElementById('mainForm').appendChild(createButton('Deletar', classBtnDel));
    createCancelButton();
}

function calHeightOfForm(numOfEleInForm){
    return (70 * numOfEleInForm) / 9;
}
