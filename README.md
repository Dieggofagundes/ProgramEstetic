# ProgramEstetic
Programa para empresas de Esteticas 

API completa para gestão de clínica de estética com:

Cadastro de clientes (nome, endereço, foto, CPF, assinatura e medidas corporais)
Cadastro de tratamentos
Lançamento de atendimentos
Agenda de pacientes por procedimento
Controle de estoque com baixa automática ao usar produtos no atendimento
Alertas de estoque baixo
Controle financeiro com cálculo de lucro real
Formas de pagamento: crédito (parcelado/à vista), débito e pix


Stack

Java 17
Spring Boot 3
Spring Web
Spring Data JPA
H2 Database

Exemplo de Atendimento com Baixa de  Estoque

{
  "clienteId": 1,
  "tratamentoId": 1,
  "dataHora": "2026-01-01T10:00:00",
  "valorCobrado": 250.00,
  "formaPagamento": "CREDITO",
  "parcelas": 3,
  "produtosConsumidos": [
    { "produtoId": 1, "quantidadeUtilizada": 2.0 }
]
}
