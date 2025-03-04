package leandrodev.service;

import leandrodev.models.Produto;
import leandrodev.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class) // Faz o Spring Boot gerenciar o contexto de testes
@SpringBootTest
class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService; // Serviço a ser testado

    @Autowired
    private ProdutoRepository produtoRepository; // Repositório usado no serviço

    private Produto produto;

    @BeforeEach
    void setUp() {
        // Configuração inicial para os testes
        produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setDescricao("Descrição do produto");
        produto.setPreco(100.0);
        produto.setQuantidadeEmEstoque(10);
    }

    @Test
    void listarTodos() {
        // Salvar produto no banco de dados
        produtoRepository.save(produto);

        // Listar todos os produtos e verificar o tamanho da lista
        assertTrue(produtoService.listarTodos().size() > 0, "A lista de produtos deve conter ao menos um item.");
    }

    @Test
    void buscarPorId() {
        // Salvar produto no banco de dados
        produtoRepository.save(produto);

        // Buscar produto pelo ID
        Optional<Produto> produtoEncontrado = produtoService.buscarPorId(produto.getId());

        assertTrue(produtoEncontrado.isPresent(), "Produto deve ser encontrado.");
        assertEquals(produto.getNome(), produtoEncontrado.get().getNome(), "Os nomes dos produtos devem ser iguais.");
    }

    @Test
    void salvar() {
        // Salvar o produto
        Produto savedProduto = produtoService.salvar(produto);

        assertNotNull(savedProduto, "Produto salvo não pode ser nulo.");
        assertEquals(produto.getNome(), savedProduto.getNome(), "Os nomes dos produtos devem ser iguais.");
    }

    @Test
    void excluir() {
        // Salvar produto no banco de dados
        produtoRepository.save(produto);

        // Excluir produto pelo ID
        produtoService.excluir(produto.getId());

        // Verificar se o produto foi realmente excluído
        Optional<Produto> produtoExcluido = produtoRepository.findById(produto.getId());
        assertFalse(produtoExcluido.isPresent(), "Produto excluído deve não ser encontrado.");
    }
}
