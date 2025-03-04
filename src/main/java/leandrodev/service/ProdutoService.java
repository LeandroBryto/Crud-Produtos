package leandrodev.service;

import leandrodev.models.Produto;
import leandrodev.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    // Logger para a classe ProdutoService
    private static final Logger logger = LoggerFactory.getLogger(ProdutoService.class);

    @Autowired
    private ProdutoRepository produtoRepository;

    // Listando todos os produtos
    public List<Produto> listarTodos() {
        logger.info("Listando todos os produtos");
        List<Produto> produtos = produtoRepository.findAll();
        logger.info("Total de produtos encontrados: {}", produtos.size());
        return produtos;
    }

    // Buscando produto por ID
    public Optional<Produto> buscarPorId(Long id) {
        logger.info("Buscando produto com ID: {}", id);
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            logger.info("Produto encontrado: {}", produto.get().getNome());
        } else {
            logger.warn("Produto com ID {} não encontrado", id);
        }
        return produto;
    }

    // Salvando produto
    public Produto salvar(Produto produto) {
        logger.info("Salvando produto: {}", produto.getNome());
        Produto savedProduto = produtoRepository.save(produto);
        logger.info("Produto salvo com sucesso: {}", savedProduto.getNome());
        return savedProduto;
    }

    // Excluindo produto
    public void excluir(Long id) {
        logger.info("Excluindo produto com ID: {}", id);
        try {
            produtoRepository.deleteById(id);
            logger.info("Produto com ID {} excluído com sucesso", id);
        } catch (Exception e) {
            logger.error("Erro ao excluir produto com ID {}: {}", id, e.getMessage());
        }
    }
}
