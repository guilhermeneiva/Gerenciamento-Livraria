const getLivros = async (categoria = '') => {
    try {
        let url = 'http://localhost:8080/livros';
        if (categoria) {
            url = `http://localhost:8080/livros/categoria/${categoria}`;
        }
        const resposta = await fetch(url);
        if (!resposta.ok) {
            throw new Error("Ops! Algo deu errado no GET.");
        }

        const dados = await resposta.json();
        console.log("Dados dos livros:", dados);

        const divContainer = document.querySelector('.estoque');
        divContainer.innerHTML = "";

        if (dados.length === 0) {
            divContainer.innerHTML = "<p>Nenhum livro encontrado para esta categoria.</p>";
            return;
        }

        dados.forEach(livro => {
            console.log("Livro:", livro);

            const div = document.createElement('div');
            div.classList.add('livro');

            div.innerHTML = `
                <img src="${livro.imgUrl}" alt="Capa do Livro">
                <h3>${livro.titulo}</h3>
                <p><strong>Autor:</strong> ${livro.autor}</p>
                <p><strong>Data de Publicação:</strong> ${livro.anoPublicacao}</p>
                <p><strong>Categoria:</strong> ${livro.categoria}</p>
                <p><strong>Quantidade:</strong> ${livro.quantidade}</p>
                <button type="submit" onclick="window.location.href='editarLivro.html?id=${livro.id}'">Editar</button>
                <button type="submit" onclick="deleteLivro('${livro.id}')">Excluir</button>
            `;

            if (livro.quantidade == 0) {
                div.innerHTML += '<p class ="status-informacao"><strong>Status:</strong> aguardando chegar no estoque.</p>';
            }

            divContainer.appendChild(div);
        });
    } catch (error) {
        console.error("Erro na requisição GET:", error.message);
    }
};

const deleteLivro = async (id) => {
    try {
        const resposta = await fetch(`http://localhost:8080/livros/${id}`, {
            method: 'DELETE'
        });

        if (!resposta.ok) {
            throw new Error("Ops! Algo deu errado ao tentar deletar o livro.");
        }

        getLivros();
    } catch (error) {
        console.error("Erro ao deletar o livro:", error.message);
    }
};

document.getElementById('buscarCategoria').addEventListener('click', () => {
    const categoria = document.getElementById('categoriaBusca').value.trim();
    console.log("Categoria buscada:", categoria);
    getLivros(categoria);
});

window.onload = () => {
    getLivros();
};

mostrarTodos = () => {
    document.getElementById('categoriaBusca').value = '';
    getLivros();
}

window.mostrarTodos = mostrarTodos;
