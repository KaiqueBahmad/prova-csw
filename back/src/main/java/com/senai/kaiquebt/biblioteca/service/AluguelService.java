package com.senai.kaiquebt.biblioteca.service;

import org.springframework.stereotype.Service;

import com.senai.kaiquebt.biblioteca.dto.AluguelRequestDTO;
import com.senai.kaiquebt.biblioteca.dto.AluguelResponseDTO;
import com.senai.kaiquebt.biblioteca.entity.Aluguel;
import com.senai.kaiquebt.biblioteca.entity.Cliente;
import com.senai.kaiquebt.biblioteca.entity.Veiculo;
import com.senai.kaiquebt.biblioteca.repository.AluguelRepository;
import com.senai.kaiquebt.biblioteca.repository.ClienteRepository;
import com.senai.kaiquebt.biblioteca.repository.VeiculoRepository;

import java.util.List;

@Service
public class AluguelService {

    private final AluguelRepository aluguelRepo;
    private final ClienteRepository clienteRepo;
    private final VeiculoRepository veiculoRepo;

    public AluguelService(AluguelRepository aluguelRepo, ClienteRepository clienteRepo, VeiculoRepository veiculoRepo) {
        this.aluguelRepo = aluguelRepo;
        this.clienteRepo = clienteRepo;
        this.veiculoRepo = veiculoRepo;
    }

    public AluguelResponseDTO alugarVeiculo(AluguelRequestDTO dto) {
        Cliente cliente = clienteRepo.findById(dto.clienteId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        Veiculo veiculo = veiculoRepo.findById(dto.veiculoId())
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado"));

        if (!veiculo.getDisponivel()) {
            throw new IllegalStateException("Veículo indisponível");
        }

        veiculo.setDisponivel(false);
        veiculoRepo.save(veiculo);

        Aluguel aluguel = new Aluguel();
        aluguel.setCliente(cliente);
        aluguel.setVeiculo(veiculo);
        aluguel.setDataInicio(dto.dataInicio());
        aluguel.setDataFim(dto.dataFim());

        aluguelRepo.save(aluguel);

        return mapToResponse(aluguel);
    }

    public void devolverVeiculo(Long aluguelId) {
        Aluguel aluguel = aluguelRepo.findById(aluguelId)
                .orElseThrow(() -> new IllegalArgumentException("Aluguel não encontrado"));

        aluguel.setAtivo(false);
        Veiculo veiculo = aluguel.getVeiculo();
        veiculo.setDisponivel(true);

        veiculoRepo.save(veiculo);
        aluguelRepo.save(aluguel);
    }

    public List<AluguelResponseDTO> listarAtivos() {
        return aluguelRepo.findByAtivoTrue().stream()
                .map(this::mapToResponse)
                .toList();
    }

    private AluguelResponseDTO mapToResponse(Aluguel aluguel) {
        return new AluguelResponseDTO(
                aluguel.getId(),
                aluguel.getCliente().getNome(),
                aluguel.getVeiculo().getModelo(),
                aluguel.getDataInicio(),
                aluguel.getDataFim(),
                aluguel.isAtivo()
        );
    }

    public List<AluguelResponseDTO> listarPorAtivo(Boolean ativo) {
        List<Aluguel> lista;

        if (ativo == null) {
            lista = aluguelRepo.findAll();
        } else if (ativo) {
            lista = aluguelRepo.findByAtivoTrue();
        } else {
            lista = aluguelRepo.findByAtivoFalse();
        }

        return lista.stream().map(this::mapToResponse).toList();
    }

}
