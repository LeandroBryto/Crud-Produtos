package leandrodev.controller;

import jakarta.validation.Valid;
import leandrodev.models.Produto;
import leandrodev.service.ProdutoService;
import leandrodev.assembler.ProdutoModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;

    @Operation(summary = "Criar um novo produto", description = "Cria um novo produto e retorna o produto criado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Entrada inválida")
    })
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody Produto produto) {
        Produto novoProduto = produtoService.salvar(produto);
        EntityModel<Produto> produtoEntityModel = produtoModelAssembler.toModel(novoProduto);
        return new ResponseEntity<>(produtoEntityModel.getContent(), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos os produtos", description = "Retorna todos os produtos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Produto>>> listarTodos() {
        List<EntityModel<Produto>> produtos = produtoService.listarTodos().stream()
                .map(produtoModelAssembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(produtos));
    }

    @Operation(summary = "Buscar produto por ID", description = "Retorna um produto específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Produto>> buscarPorId(@Parameter(description = "ID do produto a ser buscado") @PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(produto -> ResponseEntity.ok(produtoModelAssembler.toModel(produto)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar produto", description = "Atualiza as informações de um produto existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Produto>> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
        if (!produtoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        produto.setId(id);
        Produto produtoAtualizado = produtoService.salvar(produto);
        return ResponseEntity.ok(produtoModelAssembler.toModel(produtoAtualizado));
    }

    @Operation(summary = "Excluir produto", description = "Exclui um produto pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!produtoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
