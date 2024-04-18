package com.example.mspedido.service.impl;

import com.example.mspedido.entity.Pedido;
import com.example.mspedido.entity.PedidoDetalle;
import com.example.mspedido.feign.ClienteFeign;
import com.example.mspedido.feign.ProductoFeign;
import com.example.mspedido.repository.PedidoRespository;
import com.example.mspedido.service.PedidoService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    PedidoRespository pedidoRespository;
    @Autowired
    private ClienteFeign clienteFeign;
    @Autowired
    private ProductoFeign productoFeign;


    @Override
    public List<Pedido> listar() {
        return pedidoRespository.findAll();
    }

    @Override
    public Pedido guardar(Pedido pedido) {
        return pedidoRespository.save(pedido);
    }

    @Override
    public Pedido buscarPorId(Integer id) {
        Pedido pedido = pedidoRespository.findById(id).get();
        pedido.setClienteDto(clienteFeign.buscarPorId(pedido.getClienteId()).getBody());
        /*for (PedidoDetalle pedidoDetalle: pedido.getDetalle()){
            pedidoDetalle.setProducto(productoFeign.buscarPOrId(pedidoDetalle.getProductoId().getBody()));
        }*/
        List<PedidoDetalle> pedidoDetalles = pedido.getDetalle().stream().map(pedidoDetalle -> {
            pedidoDetalle.setProducto(productoFeign.buscarPorId(pedidoDetalle.getProductoId()).getBody());
            return pedidoDetalle;
        }).toList();
        pedido.setDetalle(pedidoDetalles);

        return pedido;
    }

    @Override
    public Pedido actualizar(Pedido pedido) {
        return pedidoRespository.save(pedido);
    }

    @Override
    public void eliminar(Integer id) {
        pedidoRespository.deleteById(id);

    }
}
