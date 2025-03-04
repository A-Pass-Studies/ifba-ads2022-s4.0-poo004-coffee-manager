# Use uma imagem base, como o Ubuntu
# Use a imagem oficial do PostgreSQL
FROM postgres:13

# Instale os pacotes necessários para configurar o locale pt_BR.UTF-8
# Instalar pacotes necessários e gerar o locale pt_BR.UTF-8
RUN echo pt_BR UTF-8 >> /etc/locale.gen
RUN apt-get update && apt-get install -y locales && \
    locale-gen pt_BR.UTF-8 && \
    update-locale LANG=pt_BR.UTF-8 LC_ALL=pt_BR.UTF-8

# Gere o locale desejado (por exemplo, pt_BR.UTF-8)
RUN locale-gen pt_BR.UTF-8

# Defina as variáveis de ambiente de locale
ENV LANG=pt_BR.UTF-8
ENV LC_ALL=pt_BR.UTF-8
ENV LC_COLLATE: "pt_BR.UTF-8"
ENV LC_CTYPE: "pt_BR.UTF-8"