package leandrodev.controller;

import leandrodev.models.Produto;
import leandrodev.service.ProdutoService;
import leandrodev.assembler.ProdutoModelAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Optional;

public class ProdutoControllerTest {

    @InjectMocks
    private ProdutoController produtoController;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private ProdutoModelAssembler produtoModelAssembler;

    private Produto produto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");
        produto.setPreco(100.0);
    }

    @Test
    public void testCriarProduto() {
        when(produtoService.salvar(any(Produto.class))).thenReturn(produto);
        when(produtoModelAssembler.toModel(produto)).thenReturn(EntityModel.of(produto));

        ResponseEntity<Produto> response = produtoController.criarProduto(produto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(produto.getNome(), response.getBody().getNome());
    }

    @Test
    public void testListarTodosProdutos() {
        when(produtoService.listarTodos()).thenReturn(Arrays.asList(produto));
        when(produtoModelAssembler.toModel(produto)).thenReturn(EntityModel.of(produto));

        ResponseEntity<CollectionModel<EntityModel<Produto>>> response = produtoController.listarTodos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getContent().size());
    }

    @Test
    public void testBuscarProdutoPorId() {
        when(produtoService.buscarPorId(1L)).thenReturn(Optional.of(produto));
        when(produtoModelAssembler.toModel(produto)).thenReturn(EntityModel.of(produto));

        ResponseEntity<EntityModel<Produto>> response = produtoController.buscarPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(produto.getNome(), response.getBody().getContent().getNome());
    }

    @Test
    public void testBuscarProdutoPorId_NotFound() {
        when(produtoService.buscarPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<EntityModel<Produto>> response = produtoController.buscarPorId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAtualizarProduto() {
        when(produtoService.buscarPorId(1L)).thenReturn(Optional.of(produto));
        when(produtoService.salvar(any(Produto.class))).thenReturn(produto);
        when(produtoModelAssembler.toModel(produto)).thenReturn(EntityModel.of(produto));

        ResponseEntity<EntityModel<Produto>> response = produtoController.atualizar(1L, produto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(produto.getNome(), response.getBody().getContent().getNome());
    }

    @Test
    public void testAtualizarProduto_NotFound() {
        when(produtoService.buscarPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<EntityModel<Produto>> response = produtoController.atualizar(1L, produto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testExcluirProduto() {
        when(produtoService.buscarPorId(1L)).thenReturn(Optional.of(produto));
        doNothing().when(produtoService).excluir(1L);

        ResponseEntity<Void> response = produtoController.excluir(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testExcluirProduto_NotFound() {
        when(produtoService.buscarPorId(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = produtoController.excluir(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
