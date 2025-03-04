package leandrodev.assembler;



import leandrodev.controller.ProdutoController;
import leandrodev.models.Produto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProdutoModelAssembler implements RepresentationModelAssembler<Produto, EntityModel<Produto>> {

    @Override
    public EntityModel<Produto> toModel(Produto produto) {
        // Link para o próprio produto (GET /api/produtos/{id})
        Link selfLink = linkTo(methodOn(ProdutoController.class).buscarPorId(produto.getId())).withSelfRel();

        // Link para atualizar o produto (PUT /api/produtos/{id})
        Link updateLink = linkTo(methodOn(ProdutoController.class).atualizar(produto.getId(), produto)).withRel("atualizar");

        // Link para deletar o produto (DELETE /api/produtos/{id})
        Link deleteLink = linkTo(methodOn(ProdutoController.class).excluir(produto.getId())).withRel("deletar");

        // Criar o EntityModel e adicionar os links
        return EntityModel.of(produto, selfLink, updateLink, deleteLink);
    }
}
