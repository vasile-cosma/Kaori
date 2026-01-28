const categorias = document.getElementsByClassName("nuestrasImg");

Array.from(categorias).forEach((img) => {
  img.addEventListener("mouseover", function () {
    // Para comprobar si existe hover como imagen
    if (this.dataset.hover) {
      this.src = this.dataset.hover;
    }
  });

  img.addEventListener("mouseout", function () {
    if (this.dataset.original) {
      this.src = this.dataset.original;
    }
  });
});
