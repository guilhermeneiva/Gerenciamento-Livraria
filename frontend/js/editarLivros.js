window.onload = function() {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');

    async function carregarDadosLivro() {
        console.log("Carregando dados do livro com ID:", id); 

        try {
            const response = await fetch(`http://localhost:8080/livros/${id}`);
            console.log("Resposta da API:", response); 

            if (!response.ok) {
                throw new Error("Ops! Algo deu errado na requisição GET.");
            }

            const livro = await response.json();
            console.log("Dados do livro:", livro); 

            if (livro) {
                document.getElementById('titulo').value = livro.titulo || '';
                document.getElementById('autor').value = livro.autor || '';
                document.getElementById('anoPublicacao').value = livro.anoPublicacao || '';
                document.getElementById('categoria').value = livro.categoria || '';
                document.getElementById('imgUrl').value = livro.imgUrl || '';
                document.getElementById('quantidade').value = livro.quantidade || 0;
            } else {
                console.error("Livro não encontrado.");
            }
        } catch (error) {
            console.error('Erro ao carregar os dados do livro:', error.message);
        }
    }

    async function atualizarLivro(event) {
        event.preventDefault();

        const livroAtualizado = {
            titulo: document.getElementById('titulo').value,
            autor: document.getElementById('autor').value,
            anoPublicacao: document.getElementById('anoPublicacao').value,
            categoria: document.getElementById('categoria').value,
            imgUrl: document.getElementById('imgUrl').value,
            quantidade: document.getElementById('quantidade').value
        };

        console.log("Livro atualizado enviado:", livroAtualizado); 

        try {
            const response = await fetch(`http://localhost:8080/livros/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(livroAtualizado)
            });

            if (!response.ok) {
                throw new Error("Ops! Algo deu errado ao atualizar o livro.");
            }

            const livro = await response.json();
            console.log("Livro atualizado:", livro);
            alert('Livro atualizado com sucesso!');
            window.location.href = 'estoqueLivros.html'; 
        } catch (error) {
            console.error("Erro ao atualizar o livro:", error.message);
        }
    }

    document.getElementById('editarLivroForm').addEventListener('submit', atualizarLivro);

    carregarDadosLivro();
};
