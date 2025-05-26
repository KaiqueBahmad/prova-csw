package com.senai.kaiquebt.biblioteca.service;

import org.springframework.stereotype.Service;

import com.senai.kaiquebt.biblioteca.dto.VeiculoRequestDTO;
import com.senai.kaiquebt.biblioteca.entity.Carro;
import com.senai.kaiquebt.biblioteca.entity.Moto;
import com.senai.kaiquebt.biblioteca.entity.TipoVeiculo;
import com.senai.kaiquebt.biblioteca.entity.Veiculo;
import com.senai.kaiquebt.biblioteca.repository.VeiculoRepository;

import java.util.List;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public Veiculo criarVeiculo(VeiculoRequestDTO veiculoDTO) {
        Veiculo veiculo;

        if (veiculoDTO.getTipo() == TipoVeiculo.CARRO) {
            veiculo = new Carro();
        } else if (veiculoDTO.getTipo() == TipoVeiculo.MOTO) {
            veiculo = new Moto();
        } else {
            throw new IllegalArgumentException("Tipo de veículo inválido");
        }

        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setDisponivel(veiculoDTO.getDisponivel());

        return veiculoRepository.save(veiculo);
    }
    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public List<Veiculo> listarDisponiveis() {
        return veiculoRepository.findByDisponivelTrue();
    }

    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado"));
    }

    public Veiculo atualizarDisponibilidade(Long id, boolean disponivel) {
        Veiculo veiculo = buscarPorId(id);
        veiculo.setDisponivel(disponivel);
        return veiculoRepository.save(veiculo);
    }
}
