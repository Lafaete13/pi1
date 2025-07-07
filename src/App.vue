<template>
  <div :class="['app', darkMode ? 'dark' : 'light']">
    <div class="top-bar">
      <h1>🧮 Calculadora Web</h1>
      <button @click="toggleDarkMode">
        {{ darkMode ? '☀️ Modo Claro' : '🌙 Modo Escuro' }}
      </button>
    </div>

    <div class="calculator">
      <input v-model.number="num1" type="number" placeholder="Número 1" />
      <select v-model="operacao">
        <option value="somar">+</option>
        <option value="subtrair">−</option>
        <option value="multiplicar">×</option>
        <option value="dividir">÷</option>
      </select>
      <input v-model.number="num2" type="number" placeholder="Número 2" />

      <button @click="calcular">Calcular</button>

      <div v-if="resultado !== null" class="resultado">
        Resultado: <strong>{{ resultado }}</strong>
      </div>
      <div v-if="erro" class="erro">
        ⚠️ {{ erro }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {
      num1: null,
      num2: null,
      operacao: 'somar',
      resultado: null,
      erro: '',
      darkMode: false
    }
  },
  methods: {
    async calcular() {
      this.erro = ''
      this.resultado = null

      if (this.num1 == null || this.num2 == null) {
        this.erro = 'Preencha os dois números.'
        return
      }

      try {
        const response = await fetch(
          `http://localhost:8181/calculator/${this.operacao}/${this.num1}/${this.num2}`
        )

        if (!response.ok) throw new Error('Erro na requisição.')

        const result = await response.json()
        this.resultado = result
      } catch (e) {
        this.erro = 'Erro ao calcular. Verifique os valores e tente novamente.'
      }
    },
    toggleDarkMode() {
      this.darkMode = !this.darkMode
    }
  }
}
</script>

<style scoped>
.app {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  transition: background 0.3s, color 0.3s;
  padding: 1rem;
}

.top-bar {
  text-align: center;
  margin-bottom: 2rem;
}

.top-bar button {
  background: none;
  border: 2px solid currentColor;
  padding: 0.4rem 1rem;
  border-radius: 8px;
  font-size: 0.9rem;
  cursor: pointer;
  margin-top: 0.5rem;
}

.calculator {
  background: var(--calc-bg);
  border-radius: 12px;
  padding: 2rem;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
  width: 100%;
  max-width: 400px;
}

input,
select {
  padding: 0.6rem;
  margin: 0.5rem 0.5rem 1rem 0;
  width: 100%;
  font-size: 1rem;
  border-radius: 8px;
  border: 1px solid #ccc;
}

button {
  padding: 0.6rem 1.2rem;
  font-size: 1rem;
  font-weight: 700;
  background-color: var(--btn-bg);
  color: rgb(255, 0, 208);
  border: none;
  border-radius: 8px;
  cursor: pointer;
  width: 100%;
  transition: background-color 0.3s;
}

button:hover {
  background-color: var(--btn-hover);
}

.resultado {
  margin-top: 1.2rem;
  font-size: 1.3rem;
  color: var(--success-color);
}

.erro {
  margin-top: 1rem;
  color: var(--error-color);
}

/* Modo Claro */
.light {
  background: #f7f9fb;
  color: #2c3e50;
  --calc-bg: #ffffff;
  --btn-bg: #035e63;
  --btn-hover: #05426a;
  --success-color: #27ae60;
  --error-color: #e74c3c;
}

/* Modo Escuro */
.dark {
  background: #1e1e2f;
  color: #f4f4f4;
  --calc-bg: #2c2c3e;
  --btn-bg: #ffffff;
  --btn-hover: #625269;
  --success-color: #2ecc71;
  --error-color: #e74c3c;
}
</style>
