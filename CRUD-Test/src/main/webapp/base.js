function formInput(props, index) {

    if (props.type === 'select') {
        return formSelect(props, index);
    }
    let pattern = '';
    if(props.pattern){
        pattern = 'pattern="' + props.pattern + '"';
    }
    let disable = '';
    if(props.disable){
        disable = 'disabled';
    }
    return `<input class="input-custom form-control ${props.classDiv}"
                    type="${props.type || 'text'}"
                    name="${props.name}"
                    onblur="onFocus(${index})" 
                    ${pattern}
                    ${disable}
                    value="${props.value}"
                    ${props.require ? 'required' : ''} /></br>
                <span class="error">${props.message}</span>`
}
function formSelect(props, index) {
    let optionsStr = "";
    props.options.forEach(e => {
        if(props.value === e.value){
            optionsStr += `<option value="${e.value}" selected>${e.name}</option>`;
        }else {
            optionsStr += `<option value="${e.value}" >${e.name}</option>`;
        }
    })
    const optionSelected = props.options.find(e => e.value === props.value);
    if(props.disable){
        optionsStr = `<option selected>${optionSelected.name}</option>`;
    }
    let disable = '';
    if(props.disable){
        disable = 'disabled';
    }
    return `<select class="input-custom ${props.classDiv}" placeholder="${props.placeholder}"
                    type="${props.type || 'text'}" name="${props.name}"
                    onblur="onFocus(${index})"
                    ${disable}
                    value="${props.value}"
                    ${props.require ? 'required' : ''}>
                        <option value>---Choose---</option>
                        ${optionsStr}
                    </select>
                    </br>
                    <span class="error">${props.message}</span>`
}
const onFocus = (index) => {
    const inputsForm = document.querySelectorAll('.input-custom')
    inputsForm[index].setAttribute("focused", "true");
}

document.addEventListener('invalid', (function () {
    return function (e) {
        e.preventDefault();
        e.target.onblur();
    };
})(), true);