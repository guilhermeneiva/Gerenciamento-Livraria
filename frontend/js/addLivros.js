const URL = 'http://localhost:8080/livros';

const adicionarLivro = async (evento) => {
    evento.preventDefault();

    const dados = {
        anoPublicacao: document.getElementById('anoPublicacao').value,
        autor: document.getElementById('autor').value,
        categoria: document.getElementById('categoria').value,
        imgUrl: document.getElementById('imgUrl').value,
        titulo: document.getElementById('titulo').value,
        quantidade: document.getElementById('quantidade').value 
    };

    console.log('Dados para enviar:', dados);

    const resposta = await fetch(URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dados)
    });

    if (resposta.ok) {
        alert(`Livro ${dados.titulo} foi adicionado com sucesso!`);
        window.location.href = 'estoqueLivros.html';
    } else {
        console.error('Erro ao adicionar livro:', resposta.status);
    }
};

document.addEventListener('DOMContentLoaded', () => {
    const addBookForm = document.getElementById('add-book-form');
    if (addBookForm) {
        addBookForm.addEventListener('submit', (evento) => {
            evento.preventDefault();
            console.log('Formulário enviado');
            adicionarLivro(evento);
        });
    }
});
