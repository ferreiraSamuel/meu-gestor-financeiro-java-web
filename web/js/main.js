    const form = window.document.getElementById("form");
    
    form.addEventListener("submit", (e) => {
        e.preventDefault();
        
        const tituloInputValue = window.document.getElementById("titulo").value;
        const descricaoInputValue = window.document.getElementById("descricao").value;
        const valorInputValue = window.document.getElementById("valor").value;
        const alertError = window.document.getElementById("alert-error");
        
        if (tituloInputValue && descricaoInputValue && valorInputValue) {
            alertError.style.display = "none"
            form.submit();
            return;
        }
        
        alertError.style.display = "block"        
    });
