let tasks = [];
let currentFilter = 'ALL';

const taskForm = document.getElementById('task-form');
const taskIdInput = document.getElementById('task-id');
const nomeInput = document.getElementById('nome');
const descricaoInput = document.getElementById('descricao');
const dataTerminoInput = document.getElementById('dataTermino');
const prioridadeInput = document.getElementById('prioridade');
const categoriaInput = document.getElementById('categoria');
const statusInput = document.getElementById("status");
const btnCancel = document.getElementById('btn-cancel');

const taskList = document.getElementById('task-list');
const countTodo = document.getElementById('count-todo');
const countDoing = document.getElementById('count-doing');
const countDone = document.getElementById('count-done');
const filterButtons = document.querySelectorAll('.btn-filter')

function sortTasks() {
    tasks.sort((a, b) => b.prioridade - a.prioridade);
}

function render() {
    sortTasks();
    taskList.innerHTML = '';

    const filteredTasks = tasks.filter (t => currentFilter === 'ALL' || t.status === currentFilter);

    filteredTasks.forEach(task => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
        <td><strong>${task.prioridade}</strong></td>
        <td>${task.nome}</td>
        <td>${task.descricao}</td>
        <td>${task.categoria}</td>
        <td>${task.dataTermino}</td>
        <td>${task.status}</td>
        <td>
            <button class="btn-edit" onclick="editTask('${task.id}')">Editar</button>
            <button class="btn-delete" onclick="deleteTask('${task.id}')">Excluir</button>
        </td>
       `;
        taskList.appendChild(tr);
    });

    updateCounters();
}

function updateCounters() {
    countTodo.textContent = tasks.filter(t => t.status === 'TODO').length;
    countDoing.textContent = tasks.filter(t => t.status === 'DONE').length;
    countDone.textContent = tasks.filter(t => t.status === 'DONE').length;
}

taskForm.addEventListener('submit', e => {
    e.preventDefault();

    const id = taskIdInput.value;
    const taskData = {
        id: id ? id : Date.now().toString(), //ID unico temporario
        nome: nomeInput.value,
        descricao: descricaoInput.value,
        dataTermino: dataTerminoInput.value,
        prioridade: parseInt(prioridadeInput.value),
        categoria: categoriaInput.value,
        status: statusInput.value,
    };

    if (id) {
        //Modo de ediçao (Update)
        const index = tasks.findIndex(t => t.id === id);
        if (index !== -1) tasks[index] = taskData;
    } else {
        //Modo de criaçao (Create)
        tasks.push(taskData);
    }

    resetForm();
    render();
});

window.editTask = function(id) {
    const task = tasks.find(t => t.id === id);
    if (!task) return;

    taskIdInput.value = task.id;
    nomeInput.value = task.nome;
    descricaoInput.value = task.descricao;
    dataTerminoInput.value = task.dataTermino;
    prioridadeInput.value = task.prioridade;
    categoriaInput.value = task.categoria;
    statusInput.value = task.status;

    btnCancel.classList.remove('hidden');
};

window.deleteTask = function(id) {
    tasks = tasks.filter(t => t.id !== id);
    render();
};

btnCancel.addEventListener('click', resetForm);

function resetForm() {
    taskIdInput.value = '';
    taskForm.reset();
    btnCancel.classList.add('hidden');
}

filterButtons.forEach(btn => {
    btn.addEventListener('click', e => {
        filterButtons.forEach(b => b.classList.remove('active'));
        e.target.classList.add('active');
        currentFilter = e.target.getAttribute('data-filter');
        render();
    });
});










